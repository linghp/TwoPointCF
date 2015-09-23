package com.peoit.twopointcf.ui.activity;

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

    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.safetyefficacy_ll1:
                myToast("验证短信");
                Bundle bundle = new Bundle();
                bundle.putBoolean("ischange",false);
                CommonUtil.gotoActivityWithData(this, ChangePhoneActivity.class, bundle, false);
                break;
            case R.id.safetyefficacy_ll2:
                myToast("联系在线客服");
                break;
            default:
                break;
        }

    }
}
