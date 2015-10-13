package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IPublishProject;
import com.squareup.okhttp.Request;

import java.io.File;
import java.util.Map;

/**
 * Created by ling on 2015/9/7.
 * description:
 */
public class PublishProjectPresenter extends BasePresenter<PublishProjectPresenter.OnHttpResultListener> implements IPublishProject {
    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }

    public PublishProjectPresenter(OnHttpResultListener view) {
        super(view);
    }

    public void initAdapter(Context context, int resource) {

    }

    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            mView.showProgress(false, mView.getStringbyid(R.string.networkrequest));
        }

        @Override
        public void onAfter() {
            super.onAfter();
            mView.hideProgress();
        }
    }

    @Override
    public void upload(String[] fileKeys, File[] files, Map<String, String> params) {
        OkHttpClientManager.postAsyn(URLs.CREATEPROJECT,fileKeys,files, params,
                new MyResultCallback<Object>() {
                    @Override
                    public void onError(Request request, String info,Exception e) {
                        if (TextUtils.isEmpty(info)) {
                            mView.showToast(R.string.networkerror);
                            e.printStackTrace();
                        } else {
                            mView.showToast(info);
                        }
                    }

                    @Override
                    public void onResponse(Object u) {
//                        mView.showToast(R.string.publishsuccess);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }

    /**
     * 实名认证
     */
    public void getVerified(String[] fileKeys, File[] files, Map<String, String> params){
        OkHttpClientManager.postAsyn(URLs.USER_VERIFYID, fileKeys, files, params, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("认证失败"+info);
                }
            }

            @Override
            public void onResponse(Object response) {
//                mView.onHttpResultSuccess();
                mView.showToast("认证成功");
                ((Activity) mView).finish();
            }
        }, mView);
    }
}
