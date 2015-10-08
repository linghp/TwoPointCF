package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IPersonalProfile;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * 个人简介
 * Created by zyz on 2015/10/8.
 */
public class PersonalProfilePresenter extends BasePresenter<PersonalProfilePresenter.OnHttpResultListener> implements IPersonalProfile{
    public PersonalProfilePresenter(OnHttpResultListener view) {
        super(view);
    }

    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
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
    public void getData(Map maps) {
        OkHttpClientManager.postAsyn(URLs.USER_CHANGEPASSWORD, maps, new MyResultCallback() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
//                    mView.showToast("用户名和密码不匹配");
                }
            }

            @Override
            public void onResponse(Object response) {
                if (response != null) {
                    MyLogger.i(">>>>>>>>>>>>>>>>修改个人简介" + response.toString());
                    ((Activity) mView).finish();
                }
            }
        });

    }
}
