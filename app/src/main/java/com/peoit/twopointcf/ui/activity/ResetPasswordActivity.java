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
 * 重置密码
 * Created by zyz on 2015/10/19.
 */
public class ResetPasswordActivity extends BaseActivity implements ChangePasswordPresenter.OnHttpResultListener {
    private EditText resetPasswordEt1, resetPasswordEt2;
    private TextView resetPasswordTv1;
    private String newPassword, twoPassword;

    private ChangePasswordPresenter presenter;
    private boolean isReset;
    private String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
    }

    @Override
    protected void initView() {
        resetPasswordEt1 = (EditText) findViewById(R.id.resetPassword_et1);
        resetPasswordEt2 = (EditText) findViewById(R.id.resetPassword_et2);
        resetPasswordTv1 = (TextView) findViewById(R.id.resetPassword_tv1);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        isReset = intent.getBooleanExtra("isReset", false);
        phonenum = intent.getStringExtra("phonenum");
        presenter = new ChangePasswordPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            if (TextUtils.isEmpty(phonenum)) {
                if (isReset)
                    titleView.setTitle("重置登录密码");
                else
                    titleView.setTitle("重置授权密码");
            }else {
                titleView.setTitle("找回登录密码");
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resetPassword_tv1:
                if (match()) {
                    Map<String, String> maps = new HashMap<>();
                    if (TextUtils.isEmpty(phonenum)) {
                        if (isReset) {
                                maps.put("id", localUserInfo.getUserId());
                                maps.put("newPassword", newPassword);
                                maps.put("passwordType", "10");
                                MyLogger.i("重置登录密码传入的数据：" + maps);
                                presenter.getResetPassword(maps);
                        } else {
                            maps.put("id", localUserInfo.getUserId());
                            maps.put("newPassword", newPassword);
                            maps.put("passwordType", "20");
                            MyLogger.i("重置授权密码传入的数据：" + maps);
                            presenter.getResetPassword(maps);
                        }
                    } else {
                        maps.put("id", phonenum);
                        maps.put("newPassword", newPassword);
                        maps.put("passwordType", "10");
                        MyLogger.i("忘记登录密码传入的数据：" + maps);
                        presenter.getResetPassword(maps);
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean match() {
        newPassword = resetPasswordEt1.getText().toString().trim();
        if (TextUtils.isEmpty(newPassword)) {
            showToast("请输入新密码");
            return false;
        }
        twoPassword = resetPasswordEt2.getText().toString().trim();
        if (TextUtils.isEmpty(twoPassword)) {
            showToast("请确认新密码");
            return false;
        } else {
            if (!newPassword.equals(twoPassword)) {
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
        return null;
    }
}
