package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 修改密码
 * Created by zyz on 2015/9/16.
 */
public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener{
    private EditText et_password1;
    private EditText et_password2;
    private TextView tv_define;
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
                myToast("修改密码成功");
                finish();
                break;
            default:
                break;
        }

    }
}
