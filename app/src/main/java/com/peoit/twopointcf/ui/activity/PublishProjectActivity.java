package com.peoit.twopointcf.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseFragmentActivity;
import com.peoit.twopointcf.ui.fragment.PublishFragment01;
import com.peoit.twopointcf.ui.fragment.PublishFragment02;
import com.peoit.twopointcf.ui.fragment.PublishFragment03;
import com.peoit.twopointcf.ui.fragment.PublishFragment04;
import com.peoit.twopointcf.ui.fragment.PublishFragment05;
import com.peoit.twopointcf.utils.CommonUtil;

public class PublishProjectActivity extends BaseFragmentActivity implements View.OnClickListener {
    private TextView tv_publish;
    private boolean isFromMyPublishProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_project);
    }

    public static void startThisActivity(boolean isFromMyPublishProject, Context context) {
        Intent intent = new Intent(context, PublishProjectActivity.class);
        intent.putExtra("isFromMyPublishProject", isFromMyPublishProject);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        isFromMyPublishProject=getIntent().getBooleanExtra("isFromMyPublishProject", false);
        if(isFromMyPublishProject){
            PublishFragment02 fragment = new PublishFragment02();
            addFragmentToStack(fragment,"publishfragment02");
        }

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int count = fragmentManager.getBackStackEntryCount();
                if (count == 0) {
                    titleView.setTitle(getString(R.string.title_activity_publish_project));
                    tv_publish.setText(getString(R.string.startpublish));
                } else if (count == 1) {
                    titleView.setTitle(getString(R.string.title_activity_publish_project));
                    tv_publish.setText(getString(R.string.nextstep));
                } else if (count == 2) {
                    titleView.setTitle(getString(R.string.title_activity_publish_project));
                    tv_publish.setText(getString(R.string.nextstep));
                } else if (count == 3) {
                    titleView.setTitle(getString(R.string.publishprojectbonusmodel));
                    tv_publish.setText(getString(R.string.nextstep));
                } else if (count == 4) {
                    titleView.setTitle(getString(R.string.title_activity_publish_project));
                    tv_publish.setText(getString(R.string.nextstep));
                }
            }
        });
    }

    @Override
    protected void initView() {
        tv_publish = (TextView) findViewById(R.id.tv_publish);
        tv_publish.setOnClickListener(this);
        titleView.setTitle(getString(R.string.title_activity_publish_project));
        titleView.setBack(this);
    }

    @Override
    protected void updateView() {
        if(!isFromMyPublishProject) {
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
                    addFragmentToStack(fragment,"publishfragment02");
                }else if(count == 1){
                    PublishFragment03 fragment = new PublishFragment03();
                    addFragmentToStack(fragment,"publishfragment03");
                }else if(count == 2){
                    PublishFragment04 fragment = new PublishFragment04();
                    addFragmentToStack(fragment,"publishfragment04");
                }else if(count == 3){
                    PublishFragment05 fragment = new PublishFragment05();
                    addFragmentToStack(fragment,"publishfragment05");
                }else if(count == 4){
                    new AlertDialog.Builder(this).setTitle("发布成功").setMessage("工作人员将在48小时内审核，如有问题将会与您联系。").setNegativeButton(
                            "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CommonUtil.gotoActivity(PublishProjectActivity.this,MyPublishProjectActivity.class,true);
                                }
                            }).show();
                }
                break;
            case R.id.left_btn:
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    if(isFromMyPublishProject&&fragmentManager.getBackStackEntryCount()==1){
                        finish();
                    }else {
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
            if(isFromMyPublishProject&&fragmentManager.getBackStackEntryCount()==1){
                fragmentManager.popBackStack();
                super.onBackPressed();
            }else {
                fragmentManager.popBackStack();
            }
        } else {
            super.onBackPressed();
        }
    }
}
