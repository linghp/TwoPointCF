package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
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
 * 注册
 * Created by zyz on 2015/9/7.
 */
public class RegisterActivity extends BaseActivity implements RegisterPresenter.OnHttpResultListener {
    private EditText registerEt1;
    private EditText registerEt2;
    private EditText registerEt3;
    private EditText registerEt4;
    private EditText registerEt5;
    private EditText registerEt6;
    private TextView registerTv1;
    private TextView registerTv2;
    private TextView registerTv3;
    String userName, phoneNumber, email, password, authorizationCode, verifyCode;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        registerEt1 = (EditText) findViewById(R.id.register_et1);
        registerEt2 = (EditText) findViewById(R.id.register_et2);
        registerEt3 = (EditText) findViewById(R.id.register_et3);
        registerEt4 = (EditText) findViewById(R.id.register_et4);
        registerEt5 = (EditText) findViewById(R.id.register_et5);
        registerEt6 = (EditText) findViewById(R.id.register_et6);//验证码
        registerTv1 = (TextView) findViewById(R.id.register_tv1);
        registerTv2 = (TextView) findViewById(R.id.register_tv2);
        registerTv3 = (TextView) findViewById(R.id.register_tv3);//获取验证码
    }

    @Override
    protected void initData() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("注册");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_tv1:
//                myToast("注册");
                if (match()) {
                    /*Map<String, String> maps = new HashMap<>();
                    maps.put("userName", userName);
                    maps.put("phoneNumber", phoneNumber);
                    maps.put("email", email);
                    maps.put("password", password);
                    maps.put("authorizationCode", authorizationCode);
                    maps.put("verifyCode", verifyCode);
                    presenter.getData(maps);*/
                    presenter.getValidateCode(userName,phoneNumber,email,password,authorizationCode,verifyCode);
                }
                break;
            case R.id.register_tv2:
                myToast("众筹服务协议");
                break;
            case R.id.register_tv3:
//                myToast("获取验证码");
                phoneNumber = registerEt3.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNumber)) {
                    showToast("请输入电话");
                } else {
                    if (isMobileNO(phoneNumber)) {
                        Map<String, String> maps = new HashMap<>();
                        maps.put("phoneNumber", phoneNumber);
                        presenter.getPhoneValidate(maps);
                    } else {
                        showToast("手机号不合法");
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean match() {
        userName = registerEt1.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            showToast("请输入昵称");
            return false;
        }else {
            //验证昵称是否可用
            Map<String, String> maps = new HashMap<>();
            maps.put("userName", userName);
            presenter.getUserNameValidate(maps);
        }
        email = registerEt2.getText().toString().trim();//邮箱
        if (TextUtils.isEmpty(email)) {
            showToast("请输入邮箱");
            return false;
        }else {
            if (isMobileEM(email)) {
//                presenter.getVlidateCode(email);
            } else {
                showToast("邮箱不合法");
                return false;
            }
        }

        phoneNumber = registerEt3.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        } else {
            if (isMobileNO(phoneNumber)) {
//                presenter.getVlidateCode(phoneNumber);
            } else {
                showToast("手机号不合法");
                return false;
            }
        }
        password = registerEt4.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            showToast("请输入登录密码");
            return false;
        }
        authorizationCode = registerEt5.getText().toString().trim();
        if (TextUtils.isEmpty(authorizationCode)) {
            showToast("请输入授权密码");
            return false;
        }
        verifyCode = registerEt6.getText().toString().trim();
        if (TextUtils.isEmpty(verifyCode)) {
            showToast("请输入验证码");
            return false;
        }
        return true;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return mobiles.matches(telRegex);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileEM(String email) {
        //验证邮箱
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return email.matches(str);
    }

    @Override
    public void onHttpResultSuccess() {
    }
}
