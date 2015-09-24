package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * 修改手机号/验证手机号
 * ischange true/false
 * Created by zyz on 2015/8/29.
 */
public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout boundphoneLl2,boundphoneLl3;
    private TextView tv_phonenum;
    private TextView boundphoneTv1,boundphoneTv2;
    private EditText et_phonenum,et_num;
    private boolean ischange = true;

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
        ischange = intent.getBooleanExtra("ischange",true);

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            if (ischange)
            titleView.setTitle("修改手机号码");
            else
                titleView.setTitle("验证手机号码");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boundphone_tv1:
                if (ischange) {
                    myToast("修改成功");
                    finish();
                }else {
//                    myToast("验证成功");

                    CommonUtil.gotoActivity(this,VerifySecurityQuestionActivity.class,true);
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
//        userName = loginEt1.getText().toString();
//        if (TextUtils.isEmpty(userName)) {
//            showToast("请输入账号");
//            return false;
//        }
        return true;
    }
}
