package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IPersonalProfile;
import com.peoit.twopointcf.utils.LocalUserInfo;
import com.squareup.okhttp.Request;

import java.util.HashMap;
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
        LocalUserInfo getLocalUserInfo();
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

    }
    public void getUserCaption(final String userCaption){
        Map<String, String> maps = new HashMap<>();
        maps.put("caption", userCaption);
        maps.put("userId",mView.getLocalUserInfo().getUserId());
        OkHttpClientManager.postAsyn(URLs.CHANGEUSERCAPTION, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("修改失败");
                }
            }

            @Override
            public void onResponse(Object response) {
                /*if (response != null) {

                    ((Activity) mView).finish();
                }*/
//                MyLogger.i(">>>>>>>>>>>>>>>>修改个人简介" + response.toString());
                mView.showToast("修改成功");
                mView.getLocalUserInfo().setUserCaption(userCaption);//存入数据
                ((Activity) mView).finish();

            }
        });
    }

}
