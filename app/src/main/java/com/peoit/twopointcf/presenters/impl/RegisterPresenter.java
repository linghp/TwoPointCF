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
                if (response != null){
                    mView.showToast(R.string.registersuccess);
                    MyLogger.i(">>>>>>>>>>>>>>>>注册" + response.toString());
                    ((Activity)mView).finish();
                }
            }
        });
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
                    mView.showToast("请求成功"+response.toString());
                    MyLogger.i(">>>>>>>>>>>>>>>>获取手机验证码" + response.toString());
                }
            }
        },mView);

    }

}
