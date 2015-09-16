package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * 登录
 * Created by zyz on 2015/9/7.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private ImageView loginIv1;
    private EditText loginEt1;
    private EditText loginEt2;
    private TextView loginTv1;
    private TextView loginTv2;
    private TextView loginTv3;

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

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("登录");
            titleView.hideLeftBtn();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_tv1:
//                myToast("登录");
                finish();
                break;
            case R.id.login_tv2:
                //注册
                CommonUtil.gotoActivity(this,RegisterActivity.class,false);
                break;
            case R.id.login_tv3:
//                myToast("忘记密码");
                Bundle bundle = new Bundle();
                bundle.putBoolean("ischange",false);
                CommonUtil.gotoActivityWithData(this,ChangePhoneActivity.class,bundle,false);
                break;
            default:
                break;
        }

    }
}
