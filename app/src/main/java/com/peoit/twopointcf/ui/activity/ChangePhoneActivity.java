package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.ChangePasswordPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改手机号/验证手机号
 * ischange true/false
 * Created by zyz on 2015/8/29.
 */
public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener , ChangePasswordPresenter.OnHttpResultListener{
    private LinearLayout boundphoneLl2, boundphoneLl3;
    private TextView tv_phonenum;
    private TextView boundphoneTv1, boundphoneTv2;
    private EditText et_phonenum, et_num;
    private boolean ischange = true;
    private String phonenum;
    private ChangePasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundphone);
    }

    @Override
    protected void initView() {
        tv_phonenum = (TextView) findViewById(R.id.boundphone_et1);
        boundphoneTv1 = (TextView) findViewById(R.id.boundphone_tv1);

        boundphoneLl2 = (LinearLayout) findViewById(R.id.boundphone_ll2);
        boundphoneLl3 = (LinearLayout) findViewById(R.id.boundphone_ll3);
        et_phonenum = (EditText) findViewById(R.id.boundphone_et2);
        et_num = (EditText) findViewById(R.id.boundphone_et3);
        boundphoneTv2 = (TextView) findViewById(R.id.boundphone_tv2);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        ischange = intent.getBooleanExtra("ischange", true);
        presenter = new ChangePasswordPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            if (ischange) {
                titleView.setTitle("修改手机号码");
                boundphoneLl3.setVisibility(View.GONE);
            }
            else{
                titleView.setTitle("验证手机号码");
                et_phonenum.setText(localUserInfo.getPhonenumber());
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boundphone_tv1:
                if (ischange) {
//                    myToast("修改手机号成功");
//                    finish();
                    if (match()){
                        Map<String, String> maps = new HashMap<>();
                        maps.put("mobile", phonenum);
                        maps.put("userId",localUserInfo.getUserId());
                        presenter.getChangePhone(maps);
                    }

                } else {
//                    myToast("验证成功");
//                    CommonUtil.gotoActivity(this,VerifySecurityQuestionActivity.class,true);//验证密保问题
                    CommonUtil.gotoActivity(this, ChangePasswordActivity.class, true);//修改密码
                }
                break;
            case R.id.boundphone_tv2:
                myToast("获取验证码");
                boundphoneTv2.setClickable(false);
                break;
            default:
                break;
        }

    }

    private boolean match() {
        phonenum = et_phonenum.getText().toString();
        if (TextUtils.isEmpty(phonenum)) {
            showToast("请输入手机号码");
            return false;
        }
        return true;
    }

    @Override
    public void onHttpResultSuccess() {

    }
}
