package com.peoit.twopointcf.presenters.impl;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
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
public class RegisterPresenter extends BasePresenter<RegisterPresenter.OnHttpResultListener>implements IRegister {

    public interface OnHttpResultListener extends IBaseView_Response{
        void onHttpResultSuccess();
    }
    public RegisterPresenter(OnHttpResultListener view) {
        super(view);
    }
    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T>{
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
        OkHttpClientManager.postAsyn(URLs.USER_SIGNUP, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, Exception e) {
                mView.showToast(R.string.networkerror);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Object response) {
                MyLogger.i(">>>>>>>>>>>>>>>>注册" + response.toString());
//                if (response != null){
//
//                }
            }
        }, mView);
    }

    //获取手机验证码
    public void getVlidateCode(String phoneNum){
        MyLogger.i(">>>>>>>>>>>>手机号码：" + phoneNum);
//        OkHttpClientManager.getAsyn(URLs.USER_VLIDATECODE + ,new);

    }

}
