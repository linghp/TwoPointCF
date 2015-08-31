package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

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
        if(titleView!=null) {
            titleView.setTitle("设置");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setup_ll1:
                myToast("清空缓存");
                break;
            case R.id.setup_ll2:
                myToast("关于我们");
                break;
            case R.id.setup_tv1:
                myToast("注销登录");
                break;
            default:
                break;
        }

    }
}
