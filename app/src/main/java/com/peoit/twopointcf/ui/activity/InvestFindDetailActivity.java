package com.peoit.twopointcf.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.IsConcernBean;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.ProjectDetailPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub1Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub2Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub3Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub4Fragment;
import com.peoit.twopointcf.ui.view.TagViewPager;
import com.peoit.twopointcf.utils.AbDateUtil;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.DialogTool;
import com.peoit.twopointcf.utils.Encryption;
import com.peoit.twopointcf.utils.MyLogger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 投资发现详情
 */
public class InvestFindDetailActivity extends BaseActivity implements View.OnClickListener, ProjectDetailPresenter.OnHttpResultListener {
    //@Bind(R.id.tv_toinvest)
    TextView tvToinvest;
    //@Bind(R.id.tv_lastbottom01)
    TextView tvLastBottom01;
    //@Bind(R.id.tv_lastbottom02)
    TextView tvLastBottom02;
    //@Bind(R.id.ll_lastbottom)
    LinearLayout llBottom;

    private TagViewPager tagViewPager;
    private TextView tv_subtitle, tv_bottom01, tv_bottom02, tv_bottom03;
    private LinearLayout linearLayoutsub;
    private ProgressBar progressBar;

    private BaseFragment firstFragment, secondFragment, thirdFragment, fourthFragment;
    public ProjectBean projectBean;

    private ProjectDetailPresenter presenter;
    private Map<String, String> maps_status = MyPublishProjectActivity.maps_status;

    private boolean isCancelProject = false;//取消关注
    /**
     * 0:来自我的已发项目
     * 1:来自我的已投项目
     */
    private int tag_int = -1;

    private String status_operate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_find_detail);
        //ButterKnife.bind(this);
    }

    public static void startThisActivity(ProjectBean projectBean, int tag_int, Activity context) {
        Intent intent = new Intent(context, InvestFindDetailActivity.class);
        intent.putExtra("projectBean", projectBean);
        intent.putExtra("tag_int", tag_int);
        context.startActivityForResult(intent, 100);
    }

    @Override
    protected void initView() {
        tagViewPager = (TagViewPager) findViewById(R.id.tagViewPager);
        tv_subtitle = (TextView) findViewById(R.id.tv_subtitle);
        linearLayoutsub = findViewByID_My(R.id.linearLayoutsub);
        findViewById(R.id.slide_tag1).setActivated(true);
        progressBar = findViewByID_My(R.id.progressBar);
        tv_bottom01 = findViewByID_My(R.id.tv_bottom01);
        tv_bottom02 = findViewByID_My(R.id.tv_bottom02);
        tv_bottom03 = findViewByID_My(R.id.tv_bottom03);
        llBottom = findViewByID_My(R.id.ll_lastbottom);
        tvToinvest = findViewByID_My(R.id.tv_toinvest);
        tvLastBottom01 = findViewByID_My(R.id.tv_lastbottom01);
        tvLastBottom02 = findViewByID_My(R.id.tv_lastbottom02);

        ViewGroup.LayoutParams layoutParams = tagViewPager.getLayoutParams();
        layoutParams.width = CommonUtil.getScreenWidth(this);
        layoutParams.height = layoutParams.width / 2;
        tagViewPager.setLayoutParams(layoutParams);
    }

    @Override
    protected void initData() {
        presenter = new ProjectDetailPresenter(this);

        firstFragment = new InvestFindDetailSub1Fragment();
        secondFragment = new InvestFindDetailSub2Fragment();
        thirdFragment = new InvestFindDetailSub3Fragment();
        fourthFragment = new InvestFindDetailSub4Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, firstFragment, "firstFragment").commit();

        projectBean = (ProjectBean) getIntent().getSerializableExtra("projectBean");
        tag_int = getIntent().getIntExtra("tag_int", -1);
        if (projectBean != null) {
            String[] investfinddetail_subitemvalues = {projectBean.sellStockMoney / 10000 + "万元", //融资资金
                    projectBean.investUserAmount + "", //已投人数
                    CommonUtil.twoPointConversion((projectBean.sellStockMoney + 0.0) / projectBean.totalStockMoney * 100) + "%", //出让股份
                    projectBean.perSellStockMoney + "元",//单股投资额
                    CommonUtil.twoPointConversion((projectBean.perSellStockMoney + 0.0) / projectBean.totalStockMoney * 100) + "%",//单股比例
                    projectBean.projectCity, projectBean.stockType, projectBean.endDate,//所在城市、股权类型、结束时间
                    projectBean.dividendType, CommonUtil.twoPointConversion(projectBean.dividendPercent * 100) + "%",//分红模式、分红比例
                    projectBean.stockholderPrivilege, CommonUtil.twoPointConversion(projectBean.investorEarnestPercent * 100) + "%",//股东持权、保障金比例
                    "众筹完成" + CommonUtil.twoPointConversion(projectBean.successCondition * 100) + "%", projectBean.industryType, projectBean.address,//启动条件、行业类型、详细地址
                    "点击查看", "点击查看", "点击查看"};//营业执照、信用报告、行业许可证
            String[] investfinddetail_subitemnames = getResources().getStringArray(R.array.investfinddetail_subitemname);
            final int length = investfinddetail_subitemnames.length;
            final List<List<String>> pictures = new ArrayList<>();
            pictures.add(projectBean.businessLicenses);
            pictures.add(projectBean.personCredits);
            pictures.add(projectBean.industryLicense);
            for (int i = 0; i < investfinddetail_subitemnames.length; i++) {
                View view = inflater.inflate(R.layout.activity_invest_find_detail_subitem, null);
                TextView tv01 = (TextView) view.findViewById(R.id.tv_01);
                TextView tv02 = (TextView) view.findViewById(R.id.tv_02);
                tv01.setText(investfinddetail_subitemnames[i]);
                tv02.setText(investfinddetail_subitemvalues[i]);
                linearLayoutsub.addView(view);

                //String[] details = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", projectBean.businessLicenses.get(0), projectBean.personCredits.get(0), projectBean.industryLicense.get(0)};

                //final String title = investfinddetail_subitemnames[i];
                //final String detail = details[i];
                final int i_final = i;
                if (investfinddetail_subitemvalues[i] != null && investfinddetail_subitemvalues[i].contains("点击查看")) {
                    tv02.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewPagerPhotoViewActivity.startThisActivity((ArrayList) (pictures.get(i_final - length + 3)), 0, InvestFindDetailActivity.this);
                            //SimplePhotoViewActivity.startThisActivity(title, detail, InvestFindDetailActivity.this);
                        }
                    });
                }
            }

            //判断用户是否关注项目
            Map<String, String> maps = new HashMap<>();
            maps.put("userId", localUserInfo.getUserId());
            maps.put("projectId", projectBean.id);
            presenter.getIsConcern(maps, new ProjectDetailPresenter.onIsConcern() {
                @Override
                public void onSueccess(IsConcernBean bean) {
                    if ("y".equals(bean.getConcern())) {
                        isCancelProject = true;
                        titleView.setRightBtn(R.mipmap.collection_on, InvestFindDetailActivity.this);
                    } else {
                        isCancelProject = false;
                        titleView.setRightBtn(R.mipmap.collection, InvestFindDetailActivity.this);
                    }
                }
            });
            //点击关注
            titleView.setRightBtn(R.mipmap.collection, this);

            //轮播
            tagViewPager.toUse(projectBean.projectPhotos, this);
        }
    }

    @Override
    protected void updateView() {
        if (projectBean != null) {
            titleView.setTitle(projectBean.projectName);
            tv_subtitle.setText(projectBean.projectName + "(" + projectBean.id + ")");
            if (!projectBean.status.equals(MyPublishProjectActivity.WAITING_INVESTED)) {
                findViewById(R.id.tv_toinvest).setVisibility(View.GONE);
            }
            double investedPercent = projectBean.investedAmount / (projectBean.sellStockMoney + 0.0);
            int investedPercent_int = (int) Math.round(investedPercent * 100);
            progressBar.setProgress(investedPercent_int);
            tv_bottom01.setText(investedPercent_int + "%");
            tv_bottom02.setText(CommonUtil.twoPointConversion(projectBean.investedAmount / 10000.0) + "万");
            if (!TextUtils.isEmpty(projectBean.endDate)) {
                Calendar c = new GregorianCalendar();
                int days = AbDateUtil.getOffectDay(AbDateUtil.getDateByFormat(projectBean.endDate, "yyyy-MM-dd").getTime(), c.getTime().getTime());
                if (days > 0)
                    tv_bottom03.setText(days + "天");
                else
                    tv_bottom03.setText("已结束");
            }

            //判断是不是从我的已发项目跳转过来，做相应的按钮显示及操作
            if (tag_int == 0) {
                llBottom.setVisibility(View.VISIBLE);
                tvToinvest.setVisibility(View.GONE);
                switch (projectBean.status) {
                    case MyPublishProjectActivity.WAITING_PAY:
                        tvLastBottom01.setText("支付");
                        tvLastBottom02.setVisibility(View.GONE);
                        tvLastBottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //myToast("支付");
                                status_operate = "支付";
                                verifyPassword(status_operate);
                            }
                        });
                        break;
                    case MyPublishProjectActivity.WAITING_INVESTED:
                        tvLastBottom01.setText("取消");
                        tvLastBottom02.setVisibility(View.GONE);
                        tvLastBottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                myToast("取消");
                                status_operate = "取消";
                                verifyPassword(status_operate);
                            }
                        });
                        break;
                    case MyPublishProjectActivity.VERIFY_FAILED:
                        tvLastBottom01.setText("取消");
                        tvLastBottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                myToast("取消");
                                status_operate = "取消";
                                verifyPassword(status_operate);
                            }
                        });
                        tvLastBottom02.setText("修改");
                        tvLastBottom02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                myToast("修改");
                                status_operate = "修改";
                                verifyPassword(status_operate);
                            }
                        });
                        break;
                    case MyPublishProjectActivity.INVEST_SUCCESS:
                        tvLastBottom01.setText("启动");
                        tvLastBottom02.setText("取消");
                        tvLastBottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                myToast("启动");
                                status_operate = "启动";
                                verifyPassword(status_operate);
                            }
                        });
                        tvLastBottom02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                myToast("取消");
                                status_operate = "取消";
                                verifyPassword(status_operate);
                            }
                        });
                        break;
                    default:
                        llBottom.setVisibility(View.GONE);
                }
            } else if ("已结束".equals(tv_bottom03.getText().toString().trim())) {
                tvToinvest.setVisibility(View.GONE);
            }else if(tag_int==1){
                tvToinvest.setText("取消投资");
                tvToinvest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogTool.createCommonDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "取消投资", "确定取消投资？", "确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Map<String, String> maps = new HashMap<>();
                                maps.put("investId", localUserInfo.getUserId());
                                maps.put("projectId", projectBean.id);
                                presenter.cancelInvest(maps);
                            }
                        }, "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                        myToast("取消");
                            }
                        }).show();

                    }
                });
            }


        }


    }

    private void verifyPassword(String title) {
        DialogTool.createPasswordDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, title, R.layout.password_dialog, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = (Dialog) dialogInterface;
                EditText et_password = (EditText) dialog.findViewById(R.id.et_password);
                String password = et_password.getText().toString();
                Pattern pattern = Pattern.compile("[a-zA-Z0-9]{8,}");
                Matcher matcher = pattern.matcher(password);
                if (!matcher.matches()) {
                    showToast("密码应为字母和数字的不少于8位的组合");
                    try {
                        Field field = dialog.getClass()
                                .getSuperclass().getDeclaredField(
                                        "mShowing");
                        field.setAccessible(true);
                        // 将mShowing变量设为false，表示对话框已关闭
                        field.set(dialog, false);
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    Field field = dialog.getClass()
                            .getSuperclass().getDeclaredField(
                                    "mShowing");
                    field.setAccessible(true);
                    // 将mShowing变量设为false，表示对话框已关闭
                    field.set(dialog, true);
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map<String, String> maps = new HashMap<>();
                maps.put("loginId", localUserInfo.getUsername());
                maps.put("authorizationCode", Encryption.generatePassword(password));
                presenter.getData(URLs.VEIFYAUTHORIZATIONCODE, maps);
            }
        }).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int tag = (int) v.getTag();
            //myToast(tag + "");
            ViewPagerPhotoViewActivity.startThisActivity((ArrayList) (projectBean.projectPhotos), tag, this);
        } else {
            MyLogger.i("xxx");
            switch (v.getId()) {

                case R.id.slide_tag1:
                    //商业计划
                    findViewById(R.id.slide_tag1).setActivated(true);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(false);
                    findViewById(R.id.slide_tag4).setActivated(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, firstFragment, "firstFragment").commit();
                    break;
                case R.id.slide_tag2:
                    //盈利模式
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(true);
                    findViewById(R.id.slide_tag3).setActivated(false);
                    findViewById(R.id.slide_tag4).setActivated(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, secondFragment, "secondFragment").commit();
                    break;
                case R.id.slide_tag3:
                    //团队介绍
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(true);
                    findViewById(R.id.slide_tag4).setActivated(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, thirdFragment, "thirdFragment").commit();
                    break;
                case R.id.slide_tag4:
                    //讨论
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(false);
                    findViewById(R.id.slide_tag4).setActivated(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, fourthFragment, "fourthFragment").commit();
                    //v.setActivated(true);
                    //myToast("test");
                    break;
                case R.id.tv_toinvest:
                    //我要投资
                    //CommonUtil.gotoActivity(this,InvestActivity.class,false);
                    if (localUserInfo.isLogin()) {
                        if ("已认证".equals(localUserInfo.getIsrealnamevalidated())) {
                            InvestActivity.startThisActivity(projectBean, this);
                        } else if ("立即认证".equals(localUserInfo.getIsrealnamevalidated())) {
                            DialogTool.createCommonDialog(this, R.mipmap.ic_launcher, getString(R.string.invest), "您还没有实名认证，不能投资项目，立即实名认证？", "确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    CommonUtil.gotoActivity(InvestFindDetailActivity.this, VerifiedActivity.class, false);
                                }
                            }, "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                        } else if ("审核中".equals(localUserInfo.getIsrealnamevalidated())) {
                            myToast("实名认证审核中，请耐心等待");
                        }
                    } else {
                        CommonUtil.gotoActivity(this, LoginActivity.class, false);
                    }
                    break;
                case R.id.right_btn:
                    on();
                    break;
            }
        }
    }

    public void on() {
        if (localUserInfo.isLogin()) {
            if (!isCancelProject) {
//                myToast("关注");
                Map<String, String> maps = new HashMap<>();
                maps.put("userId", localUserInfo.getUserId());
                maps.put("projectId", projectBean.id);
                maps.put("concern", "y");
                presenter.getConcernProject(maps, new ProjectDetailPresenter.onCancelProject() {
                    @Override
                    public void onSueccess(String bean) {
                        if ("true".equals(bean)) {
                            myToast("关注成功");
                            isCancelProject = true;
                            titleView.setRightBtn(R.mipmap.collection_on, InvestFindDetailActivity.this);
                        } else {
                            myToast("关注失败");
                            isCancelProject = false;
                            titleView.setRightBtn(R.mipmap.collection, InvestFindDetailActivity.this);
                        }
                    }
                });

            } else {
//                myToast("取消关注");
                Map<String, String> maps = new HashMap<>();
                maps.put("userId", localUserInfo.getUserId());
                maps.put("projectId", projectBean.id);
                maps.put("concern", "n");
                presenter.getConcernProject(maps, new ProjectDetailPresenter.onCancelProject() {
                    @Override
                    public void onSueccess(String bean) {
                        if ("true".equals(bean)) {
                            myToast("取消关注成功");
                            isCancelProject = false;
                            titleView.setRightBtn(R.mipmap.collection, InvestFindDetailActivity.this);
                        } else {
                            myToast("取消关注失败");
                            isCancelProject = true;
                            titleView.setRightBtn(R.mipmap.collection_on, InvestFindDetailActivity.this);
                        }
                    }
                });
            }
        } else {
            CommonUtil.gotoActivity(InvestFindDetailActivity.this, LoginActivity.class, false);
        }
    }


    @Override
    public void onHttpResultSuccess(String status) {
        if (status.equals("verifysuccess")) {//验证用户授权密码
            final Map<String, String> maps = new HashMap<>();
            maps.put("id", projectBean.id);
            switch (status_operate) {
                case "取消":
                    DialogTool.createPasswordDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "取消", R.layout.cancelproject_dialog, "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Dialog dialog = (Dialog) dialogInterface;
                            EditText et_cancelcause = (EditText) dialog.findViewById(R.id.et_cancelcause);
                            String cancelcause = et_cancelcause.getText().toString().trim();
                            maps.put("cancelRemarks",cancelcause);
                            final Map maps_final = maps;
                            presenter.getCancelProject(maps_final);
                        }
                    }).show();
                    break;
                case "启动":
                    presenter.getStartProject(maps);
                    break;
                case "支付":
                    presenter.payMargin(maps);
                    break;
                case "修改":
                    PublishProjectActivity.startThisActivity(true, projectBean, InvestFindDetailActivity.this);
                    break;
            }
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("status", status);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
