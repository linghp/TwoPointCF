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
import com.peoit.twopointcf.utils.Encryption;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private CountDownTimer countDownTimer;
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
        countDownTimer = CommonUtil.getCountDownTimer(registerTv3, 60000);
        //昵称失去焦点时触发
        registerEt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (registerEt1.hasFocus() == false) {
                    userName = registerEt1.getText().toString().trim();
                    if (TextUtils.isEmpty(userName)) {
                        showToast("请输入昵称");
                    } else {
                        //验证昵称是否可用
                        Map<String, String> maps = new HashMap<>();
                        maps.put("userName", userName);
                        presenter.getUserNameValidate(maps);
                    }
                }
            }
        });
        //邮箱失去焦点时触发
        registerEt2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (registerEt2.hasFocus() == false) {
                    email = registerEt2.getText().toString().trim();//邮箱
                    if (TextUtils.isEmpty(email)) {
                        showToast("请输入邮箱");
                    } else {
                        if (isMobileEM(email)) {
                            Map<String, String> maps = new HashMap<>();
                            maps.put("email", email);
                            presenter.verifyEmail(maps);
                        } else {
                            showToast("请输入正确邮箱");
                        }
                    }
                }
            }
        });
        //电话失去焦点时触发
        registerEt3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (registerEt3.hasFocus() == false) {
                    phoneNumber = registerEt3.getText().toString().trim();
                    if (TextUtils.isEmpty(phoneNumber)) {
                        showToast("请输入手机号");
                    } else {
                        if (CommonUtil.isMobileNO(phoneNumber)) {
                            //检测手机号是否被注册
                            Map<String, String> maps = new HashMap<>();
                            maps.put("phoneNumber", phoneNumber);
                            presenter.getPhoneValidate(maps);
                        } else {
                            showToast("请输入正确手机号");
                        }
                    }
                }
            }
        });
        /*//登录密码失去焦点时触发
        registerEt4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (registerEt4.hasFocus() == false) {
                    password = registerEt4.getText().toString().trim();
                    if (TextUtils.isEmpty(password)) {
                        showToast("请输入登录密码");
                    } else {
                        if (isMobilePW(password)) {
                        } else {
                            showToast("登录密码必须包含：数字、字母、符号等，且长度不得小于8位数");
                        }
                    }

                }
            }
        });
        //授权密码失去焦点时触发
        registerEt5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (registerEt5.hasFocus() == false) {
                    authorizationCode = registerEt5.getText().toString().trim();
                    if (TextUtils.isEmpty(authorizationCode)) {
                        showToast("请输入授权密码");
                    } else {
                        if (isMobilePW(authorizationCode)) {
                        } else {
                            showToast("授权密码必须包含：数字、字母、符号等，且长度不得小于8位数");
                        }
                    }
                }
            }
        });*/
        //验证码失去焦点时触发
        registerEt6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (registerEt6.hasFocus() == false) {
                    phoneNumber = registerEt3.getText().toString().trim();
                    verifyCode = registerEt6.getText().toString().trim();
                    if (TextUtils.isEmpty(phoneNumber)) {
                        showToast("请输入手机号");
                    } else if (TextUtils.isEmpty(verifyCode)) {
                        showToast("请输入验证码");
                    } else {
                        //检测验证码是否正确
                        Map<String, String> maps = new HashMap<>();
                        maps.put("phoneNumber", phoneNumber);
                        maps.put("validateCode", verifyCode);
                        presenter.getValidateCode(maps, new RegisterPresenter.onValidateCode() {
                            @Override
                            public void onSueccess(String bean) {
                                if ("y".equals(bean)) {
                                    myToast("验证码正确");
                                }
                            }
                        });
                    }

                }
            }
        });
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
                    //注册
                    Map<String, String> maps = new HashMap<>();
                    maps.put("userName", userName);
                    maps.put("phoneNumber", phoneNumber);
                    maps.put("email", email);
                    maps.put("password", Encryption.generatePassword(password));
                    maps.put("authorizationCode", Encryption.generatePassword(authorizationCode));
                    maps.put("verifyCode", verifyCode);
                    presenter.getData(maps);

                }
                break;
            case R.id.register_tv2:
                myToast("众筹服务协议");
                break;
            case R.id.register_tv3:
//                myToast("获取验证码");
                phoneNumber = registerEt3.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNumber)) {
                    showToast("请输入验证码");
                } else {
                    if (CommonUtil.isMobileNO(phoneNumber)) {
                        countDownTimer.start();//开始计时
                        //获取验证码
                        Map<String, String> maps = new HashMap<>();
                        maps.put("phoneNumber", phoneNumber);
                        presenter.getVlidateCode(maps);
                    } else {
                        showToast("请输入正确验证码");
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
        }
        email = registerEt2.getText().toString().trim();//邮箱
        if (TextUtils.isEmpty(email)) {
            showToast("请输入邮箱");
            return false;
        } else {
            if (isMobileEM(email)) {
//                presenter.getVlidateCode(email);
            } else {
                showToast("请输入正确邮箱");
                return false;
            }
        }
        phoneNumber = registerEt3.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            showToast("请输入手机号");
            return false;
        } else {
            if (CommonUtil.isMobileNO(phoneNumber)) {
//                presenter.getVlidateCode(phoneNumber);
            } else {
                showToast("请输入正确手机号");
                return false;
            }
        }
        password = registerEt4.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            showToast("请输入登录密码");
            return false;
        }

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{8,}");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            showToast("密码应为字母和数字的不少于8位的组合");
            return false;
        }

        authorizationCode = registerEt5.getText().toString().trim();
        if (TextUtils.isEmpty(authorizationCode)) {
            showToast("请输入授权密码");
            return false;
        }
        Matcher matcher2 = pattern.matcher(authorizationCode);
        if (!matcher2.matches()) {
            showToast("授权密码应为字母和数字的不少于8位的组合");
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
     * 验证邮箱格式
     */
    public static boolean isMobileEM(String email) {
        //验证邮箱
        /*String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return email.matches(str);*/
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证密码格式 密码必须包含：数字、字母、符号等，且长度不得小于8位数
     */
    /*public static boolean isMobilePW(String password) {
        String strPattern = "((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$";//6-16位的数字、字母、字符至少包含两种
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }*/
    @Override
    public void onHttpResultSuccess() {
    }

}
