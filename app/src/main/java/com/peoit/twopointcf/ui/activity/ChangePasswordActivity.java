package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.ChangePasswordPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.LocalUserInfo;
import com.peoit.twopointcf.utils.MyLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码
 * Created by zyz on 2015/9/16.
 */
public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener, ChangePasswordPresenter.OnHttpResultListener {
    private EditText et_password1;
    private EditText et_password2;
    private EditText et_password3;
    private TextView tv_define;

    private String oldPassword, newPassword,twoPassword;
    private ChangePasswordPresenter presenter;

    private boolean ispassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
    }

    @Override
    protected void initView() {
        et_password1 = (EditText) findViewById(R.id.changepassword_et1);
        et_password2 = (EditText) findViewById(R.id.changepassword_et2);
        et_password3 = (EditText) findViewById(R.id.changepassword_et3);
        tv_define = (TextView) findViewById(R.id.changepassword_tv1);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        ispassword = intent.getBooleanExtra("ispassword",false);
        presenter = new ChangePasswordPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            if (ispassword)
                titleView.setTitle("修改登录密码");
            else
                titleView.setTitle("修改授权密码");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changepassword_tv1:
                if (match()) {
                        Map<String, String> maps = new HashMap<>();
                        maps.put("oldPassword", oldPassword);
                        maps.put("newPassword", newPassword);
                        maps.put("id", localUserInfo.getUserId());
                    if (ispassword) {
                        maps.put("passwordType", "10");
                        MyLogger.i("修改登录密码传入的数据："+maps);
                        presenter.getData(maps);
                    }
                    else {
                        maps.put("passwordType", "20");
                        MyLogger.i("修改授权密码传入的数据：" + maps);
                        presenter.getData1(maps);
                    }
                }
                break;
            default:
                break;
        }

    }

    private boolean match() {
        oldPassword = et_password1.getText().toString().trim();
        if (TextUtils.isEmpty(oldPassword)) {
                showToast("请输入旧密码");
            return false;
        }
        newPassword = et_password2.getText().toString().trim();
        if (TextUtils.isEmpty(newPassword)) {
            showToast("请输入新密码");
            return false;
        }
        twoPassword = et_password3.getText().toString().trim();
        if (TextUtils.isEmpty(twoPassword)){
            showToast("请输入确认密码");
            return false;
        }else {
            if (!newPassword.equals(twoPassword)){
                showToast("确认密码与新密码不相同");
                return false;
            }
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
