package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.RegisterPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册
 * Created by zyz on 2015/9/7.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterPresenter.OnHttpResultListener {
    private EditText registerEt1;
    private EditText registerEt2;
    private EditText registerEt3;
    private EditText registerEt4;
    private EditText registerEt5;
    private EditText registerEt6;
    private TextView registerTv1;
    private TextView registerTv2;
    private TextView registerTv3;
    String userName, phoneNumber, email, password, authorizationCode, verifyCode;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        registerEt1 = (EditText) findViewById(R.id.register_et1);
        registerEt2 = (EditText) findViewById(R.id.register_et2);
        registerEt3 = (EditText) findViewById(R.id.register_et3);
        registerEt4 = (EditText) findViewById(R.id.register_et4);
        registerEt5 = (EditText) findViewById(R.id.register_et5);
        registerEt6 = (EditText) findViewById(R.id.register_et6);//验证码
        registerTv1 = (TextView) findViewById(R.id.register_tv1);
        registerTv2 = (TextView) findViewById(R.id.register_tv2);
        registerTv3 = (TextView) findViewById(R.id.register_tv3);//获取验证码
    }

    @Override
    protected void initData() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("注册");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_tv1:
//                myToast("注册");
                if (match()) {
                    Map<String, String> maps = new HashMap<>();
                    maps.put("userName", userName);
                    maps.put("phoneNumber", phoneNumber);
                    maps.put("email", email);
                    maps.put("password", password);
                    maps.put("authorizationCode", authorizationCode);
                    maps.put("verifyCode", verifyCode);
                    presenter.getData(maps);
                }
                break;
            case R.id.register_tv2:
                myToast("众筹服务协议");
                break;
            case R.id.register_tv3:
//                myToast("获取验证码");
                phoneNumber = registerEt3.getText().toString();
                if (TextUtils.isEmpty(phoneNumber)) {
                    showToast("请输入电话");
                } else
                    presenter.getVlidateCode(phoneNumber);
                break;
            default:
                break;
        }
    }

    private boolean match() {
        userName = registerEt1.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showToast("请输入昵称");
            return false;
        }
        email = registerEt2.getText().toString();//邮箱
        if (TextUtils.isEmpty(email)) {
            showToast("请输入邮箱");
            return false;
        }

        phoneNumber = registerEt3.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            showToast("请输入电话");
            return false;
        }
        password = registerEt4.getText().toString();
        if (TextUtils.isEmpty(password)) {
            showToast("请输入登录密码");
            return false;
        }
        authorizationCode = registerEt5.getText().toString();
        if (TextUtils.isEmpty(authorizationCode)) {
            showToast("请输入授权密码");
            return false;
        }
        verifyCode = registerEt6.getText().toString();
        if (TextUtils.isEmpty(verifyCode)) {
            showToast("请输入验证码");
            return false;
        }
        return true;
    }

    @Override
    public void onHttpResultSuccess() {
    }
}
