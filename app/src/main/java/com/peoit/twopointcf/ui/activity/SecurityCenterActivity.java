package com.peoit.twopointcf.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 安全中心/授权密码
 * Created by zyz on 2015/8/29.
 */
public class SecurityCenterActivity extends BaseActivity implements View.OnClickListener {
    private TextView securitycenterTv1;
    private TextView securitycenterTv2;
    private TextView securitycenterTv3;
    private boolean isSecurityCenter = false;

    public static void startThisActivity(boolean isSecurityCenter, Activity mAc) {
        Intent intent = new Intent(mAc, SecurityCenterActivity.class);
        intent.putExtra("isSecurityCenter", isSecurityCenter);
        mAc.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securitycenter);
    }

    @Override
    protected void initView() {
        securitycenterTv1 = (TextView) findViewById(R.id.securitycenter_tv1);
        securitycenterTv2 = (TextView) findViewById(R.id.securitycenter_tv2);
        securitycenterTv3 = (TextView) findViewById(R.id.securitycenter_tv3);
    }

    @Override
    protected void initData() {
        isSecurityCenter = getIntent().getBooleanExtra("isSecurityCenter", true);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            if (isSecurityCenter)
                titleView.setTitle("安全中心");
            else
                titleView.setTitle("授权密码");
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.securitycenter_tv1:
                myToast("重置支付密码");
                break;
            case R.id.securitycenter_tv2:
                myToast("重置登录密码");
                break;
            case R.id.securitycenter_tv3:
                myToast("手势");
                break;
            default:
                break;
        }
    }
}
