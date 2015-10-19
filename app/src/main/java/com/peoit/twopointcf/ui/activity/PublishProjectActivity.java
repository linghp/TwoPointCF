package com.peoit.twopointcf.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.presenters.impl.PublishProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.IPublishProject;
import com.peoit.twopointcf.ui.base.BaseFragmentActivity;
import com.peoit.twopointcf.ui.fragment.PublishFragment01;
import com.peoit.twopointcf.ui.fragment.PublishFragment02;
import com.peoit.twopointcf.ui.fragment.PublishFragment03;
import com.peoit.twopointcf.ui.fragment.PublishFragment04;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.FileUtil;
import com.peoit.twopointcf.utils.MyLogger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 发布项目
 */
public class PublishProjectActivity extends BaseFragmentActivity implements PublishProjectPresenter.OnHttpResultListener {
    private TextView tv_publish;
    private boolean isFromMyPublishProject = false;
    public HashMap<String, String> params = new HashMap<>();
    public ArrayList<String> listFileNames = new ArrayList<>();
    public ArrayList<File> listFiles = new ArrayList<>();
    private IPublishProject presenter;

    private ProjectBean projectBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_project);
    }

    public static void startThisActivity(boolean isFromMyPublishProject, ProjectBean projectBean, Context context) {
        Intent intent = new Intent(context, PublishProjectActivity.class);
        intent.putExtra("isFromMyPublishProject", isFromMyPublishProject);
        intent.putExtra("projectBean", projectBean);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter = new PublishProjectPresenter(this);
        isFromMyPublishProject = getIntent().getBooleanExtra("isFromMyPublishProject", false);
        projectBean = (ProjectBean) getIntent().getSerializableExtra("projectBean");
        if (isFromMyPublishProject) {
            PublishFragment02 fragment = new PublishFragment02();
            addFragmentToStack(fragment, "publishfragment02");
        }

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int count = fragmentManager.getBackStackEntryCount();
                if (isFromMyPublishProject) {
                    if (count == 0) {
                        titleView.setTitle("修改项目");
                        tv_publish.setText(getString(R.string.startpublish));
                    } else if (count == 1) {
                        titleView.setTitle("修改项目" + "  (1/3)");
                        tv_publish.setText(getString(R.string.nextstep));
                    } else if (count == 2) {
                        titleView.setTitle("修改项目" + "  (2/3)");
                        tv_publish.setText(getString(R.string.nextstep));
                    } else if (count == 3) {
                        titleView.setTitle("修改项目" + "  (3/3)");
                        tv_publish.setText(getString(R.string.nextstep));
                    } else if (count == 4) {
                        titleView.setTitle("修改项目");
                        tv_publish.setText(getString(R.string.nextstep));
                    }
                } else {
                    if (count == 0) {
                        titleView.setTitle(getString(R.string.title_activity_publish_project));
                        tv_publish.setText(getString(R.string.startpublish));
                    } else if (count == 1) {
                        titleView.setTitle(getString(R.string.title_activity_publish_project) + "  (1/3)");
                        tv_publish.setText(getString(R.string.nextstep));
                    } else if (count == 2) {
                        titleView.setTitle(getString(R.string.title_activity_publish_project) + "  (2/3)");
                        tv_publish.setText(getString(R.string.nextstep));
                    } else if (count == 3) {
                        titleView.setTitle(getString(R.string.title_activity_publish_project) + "  (3/3)");
                        tv_publish.setText(getString(R.string.nextstep));
                    } else if (count == 4) {
                        titleView.setTitle(getString(R.string.title_activity_publish_project));
                        tv_publish.setText(getString(R.string.nextstep));
                    }
                }
            }
        });

    }

    @Override
    protected void initView() {
        tv_publish = (TextView) findViewById(R.id.tv_publish);
        tv_publish.setOnClickListener(this);
        if (isFromMyPublishProject)
            titleView.setTitle("修改项目");
        else
            titleView.setTitle(getString(R.string.title_activity_publish_project));

        titleView.setBack(this);


    }

    @Override
    protected void updateView() {
        if (isFromMyPublishProject) {

        } else {
            PublishFragment01 fragment = new PublishFragment01();
            addFragmentToContainer(fragment, "publishfragment01");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish:
                int count = fragmentManager.getBackStackEntryCount();
                if (count == 0) {
                    PublishFragment02 fragment = new PublishFragment02();
                    addFragmentToStack(fragment, "publishfragment02");
                } else if (count == 1) {
                    PublishFragment02 fragment02 = (PublishFragment02) fragmentManager.findFragmentByTag("publishfragment02");
                    if (!fragment02.putData()) {
                        return;
                    }
                    PublishFragment03 fragment = new PublishFragment03();
                    addFragmentToStack(fragment, "publishfragment03");
                } else if (count == 2) {
                    PublishFragment03 fragment03 = (PublishFragment03) fragmentManager.findFragmentByTag("publishfragment03");
                    if (!fragment03.putData()) {
                        return;
                    }
                    PublishFragment04 fragment = new PublishFragment04();
                    addFragmentToStack(fragment, "publishfragment04");
                } else if (count == 3) {
                    PublishFragment04 fragment04 = (PublishFragment04) fragmentManager.findFragmentByTag("publishfragment04");
                    if (!fragment04.putData()) {
                        return;
                    }
                    toPublish();
                }
                break;
            case R.id.left_btn:
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    if (isFromMyPublishProject && fragmentManager.getBackStackEntryCount() == 1) {
                        finish();
                    } else {
                        fragmentManager.popBackStack();
                    }
                } else {
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            if (isFromMyPublishProject && fragmentManager.getBackStackEntryCount() == 1) {
                MyLogger.i("onBackPressed");
                fragmentManager.popBackStack();
                finish();
            } else {
                fragmentManager.popBackStack();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogger.i("onActivityResult");
    }

    public void toPublish() {
        String userid = localUserInfo.getUserId();
        if (TextUtils.isEmpty(userid)) {
            myToast("请登录");
            return;
        }

        if (isFromMyPublishProject) {
            params.put("userId", userid);
            params.put("id", projectBean.id);
//        params.put("industryType", "01");
//        params.put("projectCity", "01");
//        params.put("projectIntro", "01");
//        params.put("moneyUse", "1");
//        params.put("totalStockMoney", "1");
//        params.put("sellStockMoney", "1");
//        params.put("perSellStockMoney", "1");
//        params.put("successCondition", "80");
//        params.put("endDate", "1");
            // params.put("dividendType", "1");
//        params.put("dividendPercent", "80");
            //params.put("stockholderPrivilege", "1");
//        params.put("investorEarnestPercent", "10");

            PublishFragment02 fragment = (PublishFragment02) fragmentManager.findFragmentByTag("publishfragment02");
            if (fragment != null) {
                listFiles.clear();
                listFileNames.clear();
                ArrayList<ArrayList<PublishFragment02.PhotoData>> mDatas = fragment.mDatas;
                for (int i = 0; i < mDatas.size(); i++) {
                    for (PublishFragment02.PhotoData item : mDatas.get(i)) {
                        File file = new File(FileUtil.getPath(this, item.uri));
                        MyLogger.i(FileUtil.getPath(this, item.uri));
                        listFiles.add(file);
                        switch (i) {
                            case 0:
                                listFileNames.add("projectPictures");
                                break;
                            case 1:
                                listFileNames.add("businessLicensePictures");
                                break;
                            case 2:
                                listFileNames.add("creditReportPictures");
                                break;
                            case 3:
                                listFileNames.add("businessPermissionPictures");
                                break;
                        }
                    }
                }
            }
            String[] filenames = listFileNames.toArray(new String[0]);
            File[] files = listFiles.toArray(new File[0]);
            presenter.updateProject(filenames, files, params);

        } else {
            params.put("userId", userid);
//        params.put("projectName", "01");
//        params.put("industryType", "01");
//        params.put("projectCity", "01");
//        params.put("projectIntro", "01");
//        params.put("moneyUse", "1");
//        params.put("totalStockMoney", "1");
//        params.put("sellStockMoney", "1");
//        params.put("perSellStockMoney", "1");
//        params.put("successCondition", "80");
//        params.put("endDate", "1");
            // params.put("dividendType", "1");
//        params.put("dividendPercent", "80");
            //params.put("stockholderPrivilege", "1");
//        params.put("investorEarnestPercent", "10");

            PublishFragment02 fragment = (PublishFragment02) fragmentManager.findFragmentByTag("publishfragment02");
            if (fragment != null) {
                listFiles.clear();
                listFileNames.clear();
                ArrayList<ArrayList<PublishFragment02.PhotoData>> mDatas = fragment.mDatas;
                for (int i = 0; i < mDatas.size(); i++) {
                    for (PublishFragment02.PhotoData item : mDatas.get(i)) {
                        File file = new File(FileUtil.getPath(this, item.uri));
                        MyLogger.i(FileUtil.getPath(this, item.uri));
                        listFiles.add(file);
                        switch (i) {
                            case 0:
                                listFileNames.add("projectPictures");
                                break;
                            case 1:
                                listFileNames.add("businessLicensePictures");
                                break;
                            case 2:
                                listFileNames.add("creditReportPictures");
                                break;
                            case 3:
                                listFileNames.add("businessPermissionPictures");
                                break;
                        }
                    }
                }
            }
            String[] filenames = listFileNames.toArray(new String[0]);
            File[] files = listFiles.toArray(new File[0]);
            presenter.upload(filenames, files, params);
        }
    }

    @Override
    public void onHttpResultSuccess() {
        new AlertDialog.Builder(this).setTitle("发布成功").setMessage("工作人员将在48小时内审核，如有问题将会与您联系。").setNegativeButton(
                "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CommonUtil.gotoActivity(PublishProjectActivity.this, MyPublishProjectActivity.class, true);
                    }
                }).show();
    }
}
