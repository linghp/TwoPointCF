package com.peoit.twopointcf.presenters.impl;

import android.content.Context;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.presenters.interfaces.IInvestFind;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ling on 2015/9/7.
 * description:
 */
public class InvestFindPresenter extends BasePresenter<InvestFindPresenter.OnHttpResultListener> implements IInvestFind {
    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }

    public InvestFindPresenter(OnHttpResultListener view) {
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
    public void getData() {
        Map<String, String> maps = new HashMap<>();
        maps.put("key", "山");
        OkHttpClientManager.postAsyn("http://www.ainonggu666.com/api/product", maps,
                new MyResultCallback<Object>() {
                    @Override
                    public void onError(Request request, String info,Exception e) {
                        mView.showToast(R.string.networkerror);
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Object u) {
                        //mTv.setText(u.toString());
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }
}
