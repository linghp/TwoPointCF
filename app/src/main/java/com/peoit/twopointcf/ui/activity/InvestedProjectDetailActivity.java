package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

public class InvestedProjectDetailActivity extends BaseActivity {
    private TextView tv_subtitle;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project_detail);
        updateView();
    }

    public static void startThisActivity(String title, Context context) {
        Intent intent = new Intent(context, InvestedProjectDetailActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected void initView() {
        tv_subtitle= (TextView) findViewById(R.id.tv_subtitle);
    }
    private void updateView() {
        titleView.setTitle(getString(R.string.title_activity_invested_project_detail));
        if (TextUtils.isEmpty(title)) {

        } else {
            tv_subtitle.setText(title);
        }
    }
}
