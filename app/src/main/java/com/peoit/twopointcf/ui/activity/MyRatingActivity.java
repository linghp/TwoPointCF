package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 我的等级
 * Created by zyz on 2015/8/31.
 */
public class MyRatingActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrating);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("我的等级");
        }
    }

    @Override
    public void onClick(View view) {

    }
}
