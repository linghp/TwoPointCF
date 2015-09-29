package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * 绑定手机号码
 * Created by zyz on 2015/9/14.
 */
public class BoundPhoneNumActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout boundphoneLl1;
    private TextView boundphoneTv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundphonenum);
    }

    @Override
    protected void initView() {
        boundphoneLl1 = (LinearLayout) findViewById(R.id.boundphone_ll1);
        boundphoneTv1 = (TextView) findViewById(R.id.boundphone_tv1);
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
                CommonUtil.gotoActivity(this, ChangePhoneActivity.class, false);
                break;
            case R.id.boundphone_tv1:
                finish();
                break;
            default:
                break;
        }
    }

}
