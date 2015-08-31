package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 绑定手机号
 * Created by zyz on 2015/8/29.
 */
public class BoundPhoneActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout boundphoneLl1,boundphoneLl2,boundphoneLl3;
    private TextView tv_phonenum;
    private TextView boundphoneTv1,boundphoneTv2;
    private EditText et_phonenum,et_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundphone);
    }

    @Override
    protected void initView() {
        boundphoneLl1 = (LinearLayout) findViewById(R.id.boundphone_ll1);
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

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("绑定手机");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boundphone_ll1:
                titleView.setTitle("修改手机号");
                boundphoneLl1.setVisibility(View.GONE);
                boundphoneLl2.setVisibility(view.VISIBLE);
                boundphoneLl3.setVisibility(view.VISIBLE);
                break;
            case R.id.boundphone_tv1:
                myToast("确定");
                break;
            case R.id.boundphone_tv2:
                myToast("获取验证码");
                boundphoneTv2.setClickable(false);
                break;
            default:
                break;
        }

    }
}
