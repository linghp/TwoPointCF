package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * 安全效验
 * Created by zyz on 2015/9/21.
 */
public class SafetyEfficacyActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout safetyefficacyLl1;
    private LinearLayout safetyefficacyLl2;

    private boolean ispassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safetyefficacy);
    }
    @Override
    protected void initView() {
        safetyefficacyLl1 = (LinearLayout) findViewById(R.id.safetyefficacy_ll1);
        safetyefficacyLl2 = (LinearLayout) findViewById(R.id.safetyefficacy_ll2);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        ispassword = intent.getBooleanExtra("ispassword",false);
    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("安全效验");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.safetyefficacy_ll1:
                myToast("验证短信");
                Bundle bundle = new Bundle();
                bundle.putBoolean("ispassword",ispassword);
                CommonUtil.gotoActivityWithData(this, BoundPhoneNumActivity.class, bundle, false);
                break;
            case R.id.safetyefficacy_ll2:
                myToast("联系在线客服");
                break;
            default:
                break;
        }

    }
}
