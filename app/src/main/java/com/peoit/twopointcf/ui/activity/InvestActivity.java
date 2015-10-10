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
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.ui.base.BaseFragmentActivity;
import com.peoit.twopointcf.ui.fragment.InvestFragment01;
import com.peoit.twopointcf.ui.fragment.InvestFragment02;
import com.peoit.twopointcf.ui.fragment.InvestFragment03;
import com.peoit.twopointcf.utils.DialogTool;

public class InvestActivity extends BaseFragmentActivity {
    private TextView tv_publish;
    public ProjectBean projectBean;
    private InvestFragment01 fragment01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
    }

    public static void startThisActivity(ProjectBean projectBean, Context context) {
        Intent intent = new Intent(context, InvestActivity.class);
        intent.putExtra("projectBean", projectBean);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        tv_publish = (TextView) findViewById(R.id.tv_publish);
        tv_publish.setOnClickListener(this);
        titleView.setTitle(getString(R.string.title_activity_invest));
    }

    @Override
    protected void initData() {
        super.initData();
        projectBean = (ProjectBean) getIntent().getSerializableExtra("projectBean");

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int count = fragmentManager.getBackStackEntryCount();
                if (count == 0) {
                    titleView.setTitle(getString(R.string.title_activity_invest));
                    tv_publish.setText(getString(R.string.send_investrequest));
                } else if (count == 1) {
                    titleView.setTitle(getString(R.string.confirmmargin));
                    tv_publish.setText(getString(R.string.paynow));
                } else if (count == 2) {
                    titleView.setTitle(getString(R.string.paymargin));
                    tv_publish.setText(getString(R.string.confirmpay));
                }
            }
        });
    }

    @Override
    protected void updateView() {
        fragment01 = new InvestFragment01();
        addFragmentToContainer(fragment01, "InvestFragment01");

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_publish:
                int count = fragmentManager.getBackStackEntryCount();
                if (count == 0) {
                    fragment01.requestServer();
                } else if (count == 1) {
                    new AlertDialog.Builder(this).setTitle("注意").setMessage("支付保证金后，不支持保证金退款，请仔细和对相关信息。").setPositiveButton(
                            "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    InvestFragment03 fragment = new InvestFragment03();
                                    addFragmentToStack(fragment, "InvestFragment03");
                                }
                            }).show();

                } else if (count == 2) {

                }
                break;
        }
    }

    @Override
    public void onResultSuccess() {
        super.onResultSuccess();
        int count = fragmentManager.getBackStackEntryCount();
        switch (count) {
            case 0:
                DialogTool.createCommonDialog(this, 0, "提醒", "请确定并进入支付保证金页面，您将要支付"+Math.round(fragment01.investorEarnest)+"元保证金。", "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(projectBean!=null) {
                            InvestFragment02 fragment = new InvestFragment02();
                            addFragmentToStack(fragment, "InvestFragment02");
                        }
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myToast("取消");
                    }
                }).show();

                break;
        }
    }
}
