package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.RegisterPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证手机号
 * Created by zyz on 2015/9/14.
 */
public class BoundPhoneNumActivity extends BaseActivity implements View.OnClickListener, RegisterPresenter.OnHttpResultListener {
    private TextView boundphoneTv1, boundphoneTv2, boundphoneTv3;
    private EditText boundphoneEt1;
    private String num;
    private TimeCount time;
    private RegisterPresenter presenter;
    private boolean isphonenum;
    private boolean ispassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundphonenum);
    }

    @Override
    protected void initView() {
        boundphoneTv1 = (TextView) findViewById(R.id.boundphone_tv1);
        boundphoneEt1 = findViewByID_My(R.id.boundphone_et1);
        boundphoneTv2 = (TextView) findViewById(R.id.boundphone_tv2);
        boundphoneTv3 = (TextView) findViewById(R.id.boundphone_tv3);
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        isphonenum = intent.getBooleanExtra("isphonenum", false);
        ispassword = intent.getBooleanExtra("ispassword",false);
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("验证手机");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boundphoneTv1.setText(localUserInfo.getPhonenumber());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boundphone_tv2:
                //获取验证码
                time.start();//开始计时
                //获取验证码
                Map<String, String> maps = new HashMap<>();
                maps.put("phoneNumber", localUserInfo.getPhonenumber());
                presenter.getVlidateCode(maps);
                break;
            case R.id.boundphone_tv3:
                num = boundphoneEt1.getText().toString().trim();
                //确定,验证验证码
                Map<String, String> map = new HashMap<>();
                map.put("phoneNumber", localUserInfo.getPhonenumber());
                map.put("validateCode", num);
                presenter.getValidateCode(map, new RegisterPresenter.onValidateCode() {
                    @Override
                    public void onSueccess(String bean) {
                        if ("y".equals(bean)) {
                            if (isphonenum)
                                CommonUtil.gotoActivity(BoundPhoneNumActivity.this, ChangePhoneActivity.class, true);
                            else
                                CommonUtil.gotoActivity(BoundPhoneNumActivity.this, ChangeEmailActivity.class, true);
//                            if (ispassword)

                        }
                    }
                });

                break;
            default:
                break;
        }
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            boundphoneTv2.setText("重新获取");
            boundphoneTv2.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            boundphoneTv2.setClickable(false);
            boundphoneTv2.setText(millisUntilFinished / 1000 + "s后获取");
        }
    }


    @Override
    public void onHttpResultSuccess() {

    }
}
