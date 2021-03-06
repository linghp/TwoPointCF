package com.peoit.twopointcf.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.DataCleanManager;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.DialogTool;

/**
 * 设置/安全中心
 * Created by zyz on 2015/8/29.
 */
public class SetUpActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout setupLl1;
    private LinearLayout setupLl2;
    private TextView setupTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    @Override
    protected void initView() {
        setupLl1 = (LinearLayout) findViewById(R.id.setup_ll1);
        setupLl2 = (LinearLayout) findViewById(R.id.setup_ll2);
        setupTv1 = (TextView) findViewById(R.id.setup_tv1);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("设置");
        }
        if (!localUserInfo.isLogin()) {
            setupTv1.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setup_ll1:
                DialogTool.createCommonDialog(this, R.mipmap.ic_launcher, "清空缓存", "确认清空缓存？", "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataCleanManager.cleanInternalCache(SetUpActivity.this);
                        DataCleanManager.cleanDatabases(SetUpActivity.this);
                        DataCleanManager.cleanSharedPreference(SetUpActivity.this);
                        DataCleanManager.cleanFiles(SetUpActivity.this);
                        DataCleanManager.cleanExternalCache(SetUpActivity.this);
                        DataCleanManager.cleanApplicationData(SetUpActivity.this, "/storage/emulated/0/download/com.peoit.twopointcf");
                        DataCleanManager.cleanCustomCache("/storage/emulated/0/download/com.peoit.twopointcf");
                        myToast("已清空缓存");
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        myToast("取消");
                    }
                }).show();
                /*final List<String> strings = new ArrayList<String>();
                strings.add("清除");
                strings.add("不清除");
                strings.add("清除不清除");
                DialogTool.createRadioDialog(this, R.mipmap.ic_launcher, "清空缓存", strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myToast("选择了"+strings.get(i));
                    }
                }, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myToast("确定");
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myToast("取消");
                    }
                }).show();*/
                break;
            case R.id.setup_ll2:
                myToast("关于我们");
                break;
            case R.id.setup_tv1:
                DialogTool.createCommonDialog(this, R.mipmap.ic_launcher, "注销登录", "确认注销登录？", "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        localUserInfo.deleteUserInfo();
                        CommonUtil.gotoActivity(SetUpActivity.this, LoginActivity.class, true);
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        myToast("取消");
                    }
                }).show();
                break;
            default:
                break;
        }

    }
}
