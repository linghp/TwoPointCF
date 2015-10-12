package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.ChangePasswordPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改邮箱
 * Created by zyz on 2015/8/29.
 */
public class ChangeEmailActivity extends BaseActivity implements View.OnClickListener , ChangePasswordPresenter.OnHttpResultListener{
    private TextView tv_email;
    private EditText et_email;
    private String email;
    private ChangePasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeemail);
    }

    @Override
    protected void initView() {
        et_email = (EditText) findViewById(R.id.changeemail_et1);
        tv_email = (TextView) findViewById(R.id.changeemail_tv1);
    }

    @Override
    protected void initData() {
        presenter = new ChangePasswordPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("修改邮箱");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changeemail_tv1:
                if (match()) {
//                    myToast("修改邮箱成功");
                    Map<String, String> maps = new HashMap<>();
                    maps.put("email", email);
                    maps.put("userId", localUserInfo.getUserId());
                    presenter.getChangeEmail(maps);
                }
                break;
            default:
                break;
        }

    }

    private boolean match() {
        email = et_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            showToast("请输入您的邮箱");
            return false;
        }else {
            if (isMobileEM(email)){
            }else{
                showToast("输入的邮箱不正确");
                return false;
            }

        }
        return true;
    }
    /**
     * 验证邮箱格式
     */
    public static boolean isMobileEM(String email) {
        //验证邮箱
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return email.matches(str);
    }
    @Override
    public void onHttpResultSuccess() {

    }
}
