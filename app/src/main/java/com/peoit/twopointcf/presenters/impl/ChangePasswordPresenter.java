package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.text.TextUtils;

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
 * 修改密码
 * Created by zyz on 2015/9/24.
 */
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordPresenter.OnHttpResultListener> implements ILogin {

    public ChangePasswordPresenter(OnHttpResultListener view) {
        super(view);
    }
    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }

    @Override
    public void getData(Map maps) {
        OkHttpClientManager.postAsyn(URLs.USER_CHANGEPASSWORD, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info,Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("修改密码失败");
                }
            }

            @Override
            public void onResponse(Object response) {
                    MyLogger.i(">>>>>>>>>>>>>>>>修改密码" + response.toString());
                    mView.showToast(R.string.changesuccess);
                    ((Activity) mView).finish();
            }
        }, mView);
    }

    /**
     * 修改邮箱
     * @param map
     */
    public void getChangeEmail(Map map){
        OkHttpClientManager.postAsyn(URLs.CHANGEEMAIL, map, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("修改邮箱失败");
                }
            }

            @Override
            public void onResponse(Object response) {
                MyLogger.i(">>>>>>>>>>>>>>>>修改邮箱" + response.toString());
                mView.showToast(R.string.changesuccess);
                ((Activity) mView).finish();
            }
        });
    }

    /**
     * 修改电话
     * @param map
     */
    public void getChangePhone(Map map){
        OkHttpClientManager.postAsyn(URLs.CHANGEPHONE, map, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("修改手机号码失败");
                }
            }

            @Override
            public void onResponse(Object response) {
                MyLogger.i(">>>>>>>>>>>>>>>>修改手机" + response.toString());
                mView.showToast(R.string.changesuccess);
                ((Activity) mView).finish();
            }
        });
    }

    /**
     * 获取用户实名认证状态
     * @param map
     */
    public void getUserIsVerified(Map map){
        OkHttpClientManager.postAsyn(URLs.CHANGEPHONE, map, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("获取用户实名认证状态");
                }
            }

            @Override
            public void onResponse(Object response) {
                MyLogger.i(">>>>>>>>>>>>>>>>获取用户实名认证状态" + response.toString());
//                mView.showToast(R.string.changesuccess);
//                ((Activity) mView).finish();
            }
        });
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
}
