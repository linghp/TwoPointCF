package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.IsVerifiedBean;
import com.peoit.twopointcf.entity.UserLevelBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.ILogin;
import com.peoit.twopointcf.utils.LocalUserInfo;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.io.File;
import java.util.HashMap;
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
        LocalUserInfo getLocalUserInfo();
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
//                    MyLogger.i(">>>>>>>>>>>>>>>>修改密码" + response.toString());
                    mView.showToast(R.string.changesuccess);
                    ((Activity) mView).finish();
            }
        }, mView);
    }

    /**
     * 重置密码
     * @param maps
     */
    public void getResetPassword(Map maps) {
        OkHttpClientManager.postAsyn(URLs.USER_RESETPASSWORD, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info,Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("重置密码失败");
                }
            }

            @Override
            public void onResponse(Object response) {
//                    MyLogger.i(">>>>>>>>>>>>>>>>重置密码" + response.toString());
                mView.showToast(R.string.resetsuccess);
                ((Activity) mView).finish();
            }
        }, mView);
    }

    /**
     * 修改邮箱
     * @param email
     */
    public void getChangeEmail(final String email){
        Map<String, String> maps = new HashMap<>();
        maps.put("email", email);
        maps.put("userId", mView.getLocalUserInfo().getUserId());
        OkHttpClientManager.postAsyn(URLs.CHANGEEMAIL, maps, new MyResultCallback<Object>() {
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
//                MyLogger.i(">>>>>>>>>>>>>>>>修改邮箱" + response.toString());
                mView.showToast(R.string.changesuccess);
                mView.getLocalUserInfo().setEmail(email);
                ((Activity) mView).finish();
            }
        });
    }

    /**
     * 修改电话
     * @param phonenum
     */
    public void getChangePhone(final String phonenum){
        Map<String, String> maps = new HashMap<>();
        maps.put("mobile", phonenum);
        maps.put("userId",mView.getLocalUserInfo().getUserId());
        OkHttpClientManager.postAsyn(URLs.CHANGEPHONE, maps, new MyResultCallback<Object>() {
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
//                MyLogger.i(">>>>>>>>>>>>>>>>修改手机" + response.toString());
                mView.showToast(R.string.changesuccess);
                mView.getLocalUserInfo().setPhoneNumber(phonenum);
                ((Activity) mView).finish();
            }
        });
    }

    /**
     * 获取用户实名认证状态
     * @param map
     */
    public void getUserIsVerified(final Map map,final OnIsVerified isVerified){
        OkHttpClientManager.postAsyn(URLs.GETUSERISVERIFIED, map, new MyResultCallback<IsVerifiedBean>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
//                    mView.showToast("获取用户实名认证状态失败");
                }
            }

            @Override
            public void onResponse(IsVerifiedBean response) {
                MyLogger.i("认证状态" + response);
//                getUserLevel(map);//获取用户等级
                if (response != null){
                    isVerified.onSueccess(response);
                }


            }
        });
    }
    /**
     * 获取用户等级
     * @param map
     */
    public void getUserLevel(Map map, final OnUserLevelCallBack back){
        OkHttpClientManager.postAsyn(URLs.GETUSERLEVEL, map, new MyResultCallback<UserLevelBean>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("获取用户等级失败");
                }
            }

            @Override
            public void onResponse(UserLevelBean response) {
//                mView.showToast("获取用户等级"+ response.toString());
                MyLogger.i(">>>>>>>>获取用户等级"+ response.toString());
//                mView.onHttpResultSuccess();
                if (back!=null){
                    back.onSueccess(response);
                }
            }
        });
    }
    /**
     * 修改头像
     */
    public void getChangeAvatar(String[] fileKeys, File[] files, Map<String, String> params) {
        OkHttpClientManager.postAsyn(URLs.CHANGEAVATAR, fileKeys, files, params, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("修改失败" + info);
                }
            }

            @Override
            public void onResponse(Object response) {
//                mView.onHttpResultSuccess();
                mView.showToast("修改头像成功");
//                ((Activity) mView).finish();
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
    public interface OnUserLevelCallBack{
        void onSueccess(UserLevelBean bean);
    }
    public interface OnIsVerified{
        void onSueccess(IsVerifiedBean isVerifed);
    }
}
