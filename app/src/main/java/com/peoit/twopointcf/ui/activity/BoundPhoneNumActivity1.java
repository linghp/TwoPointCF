package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.RegisterPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证手机号(忘记密码)
 * Created by zyz on 2015/9/14.
 */
public class BoundPhoneNumActivity1 extends BaseActivity implements View.OnClickListener, RegisterPresenter.OnHttpResultListener {
    private TextView boundphoneTv2, boundphoneTv3;
    private EditText boundphoneEt1, boundphoneEt2;
    private String num, phonenum;
    private TimeCount time;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundphonenum1);
    }

    @Override
    protected void initView() {
        boundphoneEt1 = findViewByID_My(R.id.boundphone_et1);
        boundphoneEt2 = findViewByID_My(R.id.boundphone_et2);
        boundphoneTv2 = (TextView) findViewById(R.id.boundphone_tv2);
        boundphoneTv3 = (TextView) findViewById(R.id.boundphone_tv3);
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

    }

    @Override
    protected void initData() {
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boundphone_tv2:
                phonenum = boundphoneEt2.getText().toString().trim();
                if (TextUtils.isEmpty(phonenum)){
                    //获取验证码
                    time.start();//开始计时
                    //获取验证码
                    Map<String, String> maps = new HashMap<>();
                    maps.put("phoneNumber", localUserInfo.getPhonenumber());
                    presenter.getVlidateCode(maps);
                }

                break;
            case R.id.boundphone_tv3:
                num = boundphoneEt1.getText().toString().trim();
                //确定,验证验证码
                if (!TextUtils.isEmpty(num)){
                    Map<String, String> map = new HashMap<>();
                    map.put("phoneNumber", localUserInfo.getPhonenumber());
                    map.put("validateCode", num);
                    presenter.getValidateCode(map, new RegisterPresenter.onValidateCode() {
                        @Override
                        public void onSueccess(String bean) {
                            if ("y".equals(bean)) {

                            }else {
                                myToast("验证码验证错误");
                            }
                        }
                    });
                }else {
                    myToast("请输入验证码");
                }
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
