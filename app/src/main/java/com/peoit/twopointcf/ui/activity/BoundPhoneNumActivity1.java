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
import com.peoit.twopointcf.utils.CommonUtil;

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
    private CountDownTimer countDownTimer;
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
        countDownTimer = CommonUtil.getCountDownTimer(boundphoneTv2, 60000);

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
                if (TextUtils.isEmpty(phonenum)) {
                    myToast("请输入手机号");
                    return;
                }
                if (!CommonUtil.isMobileNO(phonenum)) {
                    myToast("请输入正确的手机号");
                    return;
                }
                //获取验证码
                countDownTimer.start();//开始计时
                //获取验证码
                Map<String, String> maps = new HashMap<>();
                maps.put("phoneNumber", phonenum);
                presenter.getVlidateCode(maps);
                break;

            case R.id.boundphone_tv3:
                num = boundphoneEt1.getText().toString().trim();
                phonenum = boundphoneEt2.getText().toString().trim();
                if (TextUtils.isEmpty(phonenum)) {
                    myToast("请输入手机号");
                    return;
                }
                if (!CommonUtil.isMobileNO(phonenum)) {
                    myToast("请输入正确的手机号");
                    return;
                }
                //确定,验证验证码
                if (!TextUtils.isEmpty(num)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("phoneNumber", phonenum);
                    map.put("validateCode", num);
                    presenter.getValidateCode(map, new RegisterPresenter.onValidateCode() {
                        @Override
                        public void onSueccess(String bean) {
                            if ("y".equals(bean)) {
                                Bundle bundle = new Bundle();
                                bundle.putString("phonenum", phonenum + "");
                                CommonUtil.gotoActivityWithData(BoundPhoneNumActivity1.this, ResetPasswordActivity.class, bundle, true);
                            } else {
                                myToast("验证码验证错误");
                            }
                        }
                    });
                } else {
                    myToast("请输入验证码");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onHttpResultSuccess() {

    }
}
