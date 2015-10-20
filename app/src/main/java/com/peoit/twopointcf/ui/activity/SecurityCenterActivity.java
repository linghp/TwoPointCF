package com.peoit.twopointcf.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * 安全中心/授权密码
 * Created by zyz on 2015/8/29.
 */
public class SecurityCenterActivity extends BaseActivity implements View.OnClickListener {
    private TextView securitycenterTv1;
    private TextView securitycenterTv2;
    private TextView securitycenterTv3;
    private TextView securitycenterTv4;
    private TextView securitycenterTv5;
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
        securitycenterTv4 = (TextView) findViewById(R.id.securitycenter_tv4);
        securitycenterTv5 = (TextView) findViewById(R.id.securitycenter_tv5);
    }

    @Override
    protected void initData() {
        isSecurityCenter = getIntent().getBooleanExtra("isSecurityCenter", true);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            if (isSecurityCenter) {
                titleView.setTitle("安全中心");
                findViewById(R.id.securitycenter_ll1).setVisibility(View.VISIBLE);
                findViewById(R.id.securitycenter_ll2).setVisibility(View.GONE);
            }
            else {
                titleView.setTitle("授权密码");
                findViewById(R.id.securitycenter_ll2).setVisibility(View.VISIBLE);
                findViewById(R.id.securitycenter_ll1).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.securitycenter_tv1:
//                myToast("修改登录密码");
                Bundle bundle = new Bundle();
                bundle.putBoolean("ispassword", true);
                CommonUtil.gotoActivityWithData(this, ChangePasswordActivity.class, bundle, true);
                break;
            case R.id.securitycenter_tv2:
//                myToast("忘记登录密码");
                Bundle bundle2 = new Bundle();
                bundle2.putString("ispassword", "true");
                CommonUtil.gotoActivityWithData(this, SafetyEfficacyActivity.class, bundle2,false);
                break;
            case R.id.securitycenter_tv3:
                myToast("手势");
                break;
            case R.id.securitycenter_tv4:
//                myToast("修改授权密码");
                Bundle bundle1 = new Bundle();
                bundle1.putBoolean("ispassword",false);
                CommonUtil.gotoActivityWithData(this, ChangePasswordActivity.class, bundle1, true);
                break;
            case R.id.securitycenter_tv5:
//                myToast("忘记授权密码");
                Bundle bundle3 = new Bundle();
                bundle3.putString("ispassword", "false");
                CommonUtil.gotoActivityWithData(this, SafetyEfficacyActivity.class, bundle3, false);
                break;
            default:
                break;
        }
    }
}
