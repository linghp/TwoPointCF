package com.peoit.twopointcf.presenters.impl;

import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IInvestProject;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by ling on 2015/9/7.
 * description:
 */
public class InvestProjectPresenter extends BasePresenter<InvestProjectPresenter.OnHttpResultListener> implements IInvestProject {
    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }

    public InvestProjectPresenter(OnHttpResultListener view) {
        super(view);
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
    public void toInvest(Map<String, String> params) {

        OkHttpClientManager.postAsyn(URLs.INVESTPROJECT, params,
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
                        mView.showToast(R.string.publishsuccess);
//                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }
}
