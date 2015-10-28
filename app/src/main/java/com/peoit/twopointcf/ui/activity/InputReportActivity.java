package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.BusinessManagerPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 录入报表 （添加财务报告）
 * Created by zyz on 2015/10/28.
 */
public class InputReportActivity extends BaseActivity implements BusinessManagerPresenter.OnHttpResultListener {
    private EditText etInputreport1;
    private EditText etInputreport2;
    private EditText etInputreport3;
    private TextView tvInputreport;
    private String income, expense, remarks;
    private BusinessManagerPresenter presenter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputreport);
    }
    public static void startThisActivity(String id,Context context) {
        Intent intent = new Intent(context, InputReportActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
    @Override
    protected void initView() {
        etInputreport1 = (EditText) findViewById(R.id.et_inputreport1);
        etInputreport2 = (EditText) findViewById(R.id.et_inputreport2);
        etInputreport3 = (EditText) findViewById(R.id.et_inputreport3);
        tvInputreport = (TextView) findViewById(R.id.tv_inputreport);
        tvInputreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (match()) {
                    params.put("income", income);
                    params.put("expense", expense);
                    params.put("remarks", remarks);
                    params.put("projectId", id);//项目id
                    params.put("data", "");
                    presenter.getData(URLs.PUBLISHREPORT,params);
                }
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new BusinessManagerPresenter(this);
        id=getIntent().getStringExtra("id");
    }

    private boolean match() {
        income = etInputreport1.getText().toString().trim();
        if (TextUtils.isEmpty(income)) {
            showToast("请输入今日收入");
            return false;
        }
        expense = etInputreport2.getText().toString().trim();
        if (TextUtils.isEmpty(expense)) {
            showToast("请输入今日支出");
            return false;
        }
        remarks = etInputreport3.getText().toString().trim();
        if (TextUtils.isEmpty(remarks)) {
            showToast("请输入备注");
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("录入报表");
        }

    }

    @Override
    public void onHttpResultSuccess() {

    }
}
