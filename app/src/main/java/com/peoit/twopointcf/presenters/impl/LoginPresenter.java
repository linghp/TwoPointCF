package com.peoit.twopointcf.presenters.impl;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.ILogin;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * 登录
 * Created by zyz on 2015/9/24.
 */
public class LoginPresenter extends BasePresenter<LoginPresenter.OnHttpResultListener> implements ILogin {

    public LoginPresenter(OnHttpResultListener view) {
        super(view);
    }

    @Override
    public void getData(Map maps) {
        OkHttpClientManager.postAsyn(URLs.USER_SIGNIN, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, Exception e) {
                mView.showToast(R.string.networkerror);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Object response) {
                MyLogger.i(">>>>>>>>>>>>>>>>登录" + response.toString());
//                if (response != null){
//
//                }
            }
        }, mView);
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
    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }
}
