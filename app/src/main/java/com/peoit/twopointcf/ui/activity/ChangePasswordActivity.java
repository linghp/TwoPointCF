package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码
 * Created by zyz on 2015/9/16.
 */
public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener{
    private EditText et_password1;
    private EditText et_password2;
    private TextView tv_define;

    private String oldPassword,newPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
    }


    private void assignViews() {

        et_password1 = (EditText) findViewById(R.id.changepassword_tv1);
        et_password2 = (EditText) findViewById(R.id.changepassword_et1);
        tv_define = (TextView) findViewById(R.id.changepassword_tv2);
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
            titleView.setTitle("设置新密码");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.changepassword_tv2:
                if (match()){
                    Map<String, String> maps = new HashMap<>();
                    maps.put("oldPassword", oldPassword);
                    maps.put("newPassword", newPassword);
//                    maps.put("passwordType", passwordType);
//                    maps.put("id", id);
                }
                finish();
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
        return true;
    }
}
