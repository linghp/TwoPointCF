package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.RegisterBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IRegister;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册
 * Created by zyz on 2015/9/24.
 */
public class RegisterPresenter extends BasePresenter<RegisterPresenter.OnHttpResultListener> implements IRegister {

    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }

    public RegisterPresenter(OnHttpResultListener view) {
        super(view);
    }

    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T> {
        @Override
        public void onBefore(Request request) {
            mView.showProgress(false, mView.getStringbyid(R.string.networkrequest));
            super.onBefore(request);
        }

        @Override
        public void onAfter() {
            mView.hideProgress();
            super.onAfter();
        }
    }

    @Override
    public void initView(OnHttpResultListener view) {
        super.initView(view);
    }

    @Override
    public void getData(Map maps) {
        //注册
        OkHttpClientManager.postAsyn(URLs.USER_SIGNUP, maps, new MyResultCallback<RegisterBean>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast(info);
                }
            }

            @Override
            public void onResponse(RegisterBean response) {
                if (response != null) {
                    mView.showToast(R.string.registersuccess);
                    MyLogger.i(">>>>>>>>>>>>>>>>注册" + response.toString());
                    ((Activity) mView).finish();
                }
            }
        });
    }

    //验证手机验证码
    public void getValidateCode(String userName,String phoneNumber,String email,String password,String authorizationCode,String verifyCode){
        final Map<String, String> maps = new HashMap<>();
        maps.put("userName", userName);
        maps.put("phoneNumber", phoneNumber);
        maps.put("email", email);
        maps.put("password", password);
        maps.put("authorizationCode", authorizationCode);
        maps.put("verifyCode", verifyCode);

        Map<String, String> map = new HashMap<>();
        maps.put("phoneNumber", phoneNumber);
        maps.put("validateCode", verifyCode);
        OkHttpClientManager.postAsyn(URLs.USER_VALIDATECODE, map, new MyResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                if (response != null){
                    mView.showToast("验证成功");
                    MyLogger.i(">>>>>>>>>验证手机验证码" + response.toString());
                    getData(maps);
                }
            }
        },mView);
    }
    //获取手机验证码
    public void getVlidateCode(Map maps) {
        OkHttpClientManager.postAsyn(URLs.USER_VLIDATECODE, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast(info);
                }
            }

            @Override
            public void onResponse(Object response) {
                if (response != null){
                    mView.showToast("请求成功" + response.toString());
                    MyLogger.i(">>>>>>>>>>>>>>>>获取手机验证码" + response.toString());

                }
            }
        },mView);
    }

    //验证手机号是否已被注册
    public void getPhoneValidate(final Map map){
        OkHttpClientManager.postAsyn(URLs.USER_PHONEVALIDATE, map, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast(info);
                }
            }

            @Override
            public void onResponse(Object response) {
                if (response != null){
                    mView.showToast("请求成功" + response.toString());
                    MyLogger.i(">>>>>>>>>>>>>>>>验证手机号是否已被注册" + response.toString());
                    getVlidateCode(map);
                }
            }
        });
    }

}
