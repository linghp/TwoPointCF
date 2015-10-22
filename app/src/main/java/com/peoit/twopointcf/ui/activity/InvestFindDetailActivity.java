package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.IsConcernBean;
import com.peoit.twopointcf.entity.ProjectBean;
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
import com.peoit.twopointcf.utils.MyLogger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private boolean isFromMyPublishProject = false;//是否来自我的已发项目


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_find_detail);
        //ButterKnife.bind(this);
    }

    public static void startThisActivity(ProjectBean projectBean, boolean isFromMyPublishProject, Context context) {
        Intent intent = new Intent(context, InvestFindDetailActivity.class);
        intent.putExtra("projectBean", projectBean);
        intent.putExtra("isFromMyPublishProject", isFromMyPublishProject);
        context.startActivity(intent);
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
        layoutParams.height = layoutParams.width / 3;
        tagViewPager.setLayoutParams(layoutParams);
        //轮播
        List<Integer> imgLists = new ArrayList<>();
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        tagViewPager.toUse(imgLists, this);
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
        isFromMyPublishProject = getIntent().getBooleanExtra("isFromMyPublishProject", false);
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
            for (int i = 0; i < investfinddetail_subitemnames.length; i++) {
                View view = inflater.inflate(R.layout.activity_invest_find_detail_subitem, null);
                TextView tv01 = (TextView) view.findViewById(R.id.tv_01);
                TextView tv02 = (TextView) view.findViewById(R.id.tv_02);
                tv01.setText(investfinddetail_subitemnames[i]);
                tv02.setText(investfinddetail_subitemvalues[i]);
                linearLayoutsub.addView(view);

                String[] details = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", projectBean.businessLicenses.get(0), projectBean.personCredits.get(0), projectBean.industryLicense.get(0)};
                final String title = investfinddetail_subitemnames[i];
                final String detail = details[i];
                if (investfinddetail_subitemvalues[i] != null && investfinddetail_subitemvalues[i].contains("点击查看")) {
                    tv02.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SimplePhotoViewActivity.startThisActivity(title, detail, InvestFindDetailActivity.this);
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
            if (isFromMyPublishProject) {
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
                                DialogTool.createCommonDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "支付", "确认支付我们将跳转到支付界面？", "确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Map<String, String> maps = new HashMap<>();
                                        maps.put("id", projectBean.id);
                                        presenter.payMargin(maps);
                                    }
                                }, "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();
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
                                DialogTool.createCommonDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "取消", "确认取消该项目的投资？", "确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Map<String, String> maps = new HashMap<>();
                                        maps.put("id", projectBean.id);
                                        presenter.getCancelProject(maps);
                                    }
                                }, "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();


                            }
                        });
                        break;
                    case MyPublishProjectActivity.VERIFY_FAILED:
                        tvLastBottom01.setText("修改");
                        tvLastBottom02.setVisibility(View.GONE);
                        tvLastBottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                myToast("修改");
                                DialogTool.createCommonDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "修改", "确认修改该项目？", "确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        PublishProjectActivity.startThisActivity(true, projectBean, InvestFindDetailActivity.this);
                                    }
                                }, "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();
                            }
                        });
                        break;
                    case MyPublishProjectActivity.INVEST_FAILED:
                        tvLastBottom01.setText("延期");
                        tvLastBottom02.setVisibility(View.GONE);
                        tvLastBottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogTool.createCommonDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "延期", "确认延期该项目？", "确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        myToast("已确认延期，正在审核");
                                    }
                                }, "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();

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
                                DialogTool.createCommonDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "启动", "确认启动该项目？", "确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Map<String, String> maps = new HashMap<>();
                                        maps.put("id", projectBean.id);
                                        presenter.getStartProject(maps);
                                    }
                                }, "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();

                            }
                        });
                        tvLastBottom02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                myToast("取消");
                                DialogTool.createCommonDialog(InvestFindDetailActivity.this, R.mipmap.ic_launcher, "取消", "确认取消该项目的投资？", "确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Map<String, String> maps = new HashMap<>();
                                        maps.put("id", projectBean.id);
                                        presenter.getCancelProject(maps);
                                    }
                                }, "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();
                            }
                        });
                        break;
                    default:
                        llBottom.setVisibility(View.GONE);
                }
            } else {
                if ("已结束".equals(tv_bottom03.getText().toString().trim())) {
                    tvToinvest.setVisibility(View.GONE);
                }

            }


        }


    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int tag = (int) v.getTag();
            switch (tag) {//轮播图的每一张的tag
                case 0:
                    // myToast(tag);
                case 1:
                    // myToast(tag);
                case 2:
                    //myToast(tag);
                case 3:
                    myToast(tag + "");
                    break;
            }
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
                        InvestActivity.startThisActivity(projectBean, this);
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
    public void onHttpResultSuccess() {

    }
}
