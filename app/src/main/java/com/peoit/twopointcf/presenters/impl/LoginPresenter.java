package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.RegisterBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.ILogin;
import com.peoit.twopointcf.utils.LocalUserInfo;
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
        OkHttpClientManager.postAsyn(URLs.USER_SIGNIN, maps, new MyResultCallback<RegisterBean>() {
            @Override
            public void onError(Request request, String info,Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("用户名和密码不匹配");
                }
            }

            @Override
            public void onResponse(RegisterBean response) {
                if (response != null) {
                    MyLogger.i(">>>>>>>>>>>>>>>>登录" + response.toString());
                    mView.showToast(R.string.loginsuccess);
                    LocalUserInfo.putUser(response);
                            ((Activity) mView).finish();
                }
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
