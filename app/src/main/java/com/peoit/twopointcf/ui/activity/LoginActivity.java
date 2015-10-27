package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.LoginPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.LocalUserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录
 * Created by zyz on 2015/9/7.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginPresenter.OnHttpResultListener {
    private ImageView loginIv1;
    private EditText loginEt1;
    private EditText loginEt2;
    private TextView loginTv1;
    private TextView loginTv2;
    private TextView loginTv3;
    private String userName;
    private String password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        loginIv1 = (ImageView) findViewById(R.id.login_iv1);
        loginEt1 = (EditText) findViewById(R.id.login_et1);
        loginEt2 = (EditText) findViewById(R.id.login_et2);
        loginTv1 = (TextView) findViewById(R.id.login_tv1);
        loginTv2 = (TextView) findViewById(R.id.login_tv2);
        loginTv3 = (TextView) findViewById(R.id.login_tv3);


    }

    @Override
    protected void initData() {
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("登录");
            titleView.hideLeftBtn();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv1:
//                myToast("登录");
                if (match()) {
                    Map<String, String> maps = new HashMap<>();
                    maps.put("loginId", userName);
                    maps.put("password", password);
                    presenter.getData(maps);
                }
                break;
            case R.id.login_tv2:
                //注册
                CommonUtil.gotoActivity(this, RegisterActivity.class, false);
                break;
            case R.id.login_tv3:
//                myToast("忘记密码");
                CommonUtil.gotoActivity(this,BoundPhoneNumActivity1.class,false);
                break;
            default:
                break;
        }

    }

    private boolean match() {
        userName = loginEt1.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            showToast("请输入账号");
            return false;
        }

        password = loginEt2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            showToast("请输入密码");
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{8,}");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            showToast("密码应为字母和数字的不少于8位的组合");
            return false;
        }

        return true;
    }

    @Override
    public void onHttpResultSuccess() {

    }

    @Override
    public LocalUserInfo getLocalUserInfo() {
        return localUserInfo;
    }
}
