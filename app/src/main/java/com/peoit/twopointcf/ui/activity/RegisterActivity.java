package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 注册
 * Created by zyz on 2015/9/7.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private EditText registerEt1;
    private EditText registerEt2;
    private EditText registerEt3;
    private EditText registerEt4;
    private EditText registerEt5;
    private TextView registerTv1;
    private TextView loginTv3;

    private void assignViews() {
        registerEt1 = (EditText) findViewById(R.id.register_et1);
        registerEt2 = (EditText) findViewById(R.id.register_et2);
        registerEt3 = (EditText) findViewById(R.id.register_et3);
        registerEt4 = (EditText) findViewById(R.id.register_et4);
        registerEt5 = (EditText) findViewById(R.id.register_et5);
        registerTv1 = (TextView) findViewById(R.id.register_tv1);
        loginTv3 = (TextView) findViewById(R.id.login_tv3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
            titleView.setTitle("注册");
        }
    }

    @Override
    public void onClick(View view) {

    }
}
