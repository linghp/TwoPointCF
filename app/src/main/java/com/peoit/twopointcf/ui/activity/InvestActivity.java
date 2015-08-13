package com.peoit.twopointcf.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseFragmentActivity;
import com.peoit.twopointcf.ui.fragment.InvestFragment01;
import com.peoit.twopointcf.ui.fragment.InvestFragment02;
import com.peoit.twopointcf.ui.fragment.InvestFragment03;

public class InvestActivity extends BaseFragmentActivity {
    private TextView tv_publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
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
        InvestFragment01 fragment = new InvestFragment01();
        addFragmentToContainer(fragment, "InvestFragment01");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_publish:
                int count = fragmentManager.getBackStackEntryCount();
                if (count == 0) {
                    new AlertDialog.Builder(this).setTitle("提醒").setMessage("请确定并进入支付保证金页面，您将要支付5千元保证金。").setPositiveButton(
                            "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    InvestFragment02 fragment = new InvestFragment02();
                                    addFragmentToStack(fragment, "InvestFragment02");
                                }
                            }).setNegativeButton("取消", null).show();
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
}
