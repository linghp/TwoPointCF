package com.peoit.twopointcf.presenters.impl;

import android.content.Context;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.InformationCenterBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IFindInformationsProject;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资讯中心
 * Created by ling on 2015/9/7.
 * description:
 */
public class FindInformationsPresenter extends BasePresenter<FindInformationsPresenter.OnHttpResultListener> implements IFindInformationsProject {
    private List<InformationCenterBean> informationBeans;
    private int offset=0;
    private int pageSize=10;
    Map<String, String> maps = new HashMap<>();
    @Override
    public void getData(List<InformationCenterBean> informationBeans) {
        this.informationBeans=informationBeans;
        maps.put("offset",0+"");
        maps.put("pageSize",pageSize+"");
        OkHttpClientManager.postAsyn(URLs.FINDINFORMATIONS, maps,
                new MyResultCallback<List<InformationCenterBean>>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        if (TextUtils.isEmpty(info)) {
                            mView.showToast(R.string.networkerror);
                            e.printStackTrace();
                        } else {
                            mView.showToast(info);
                        }
//                        mView.showErrorPage();

                    }

                    @Override
                    public void onResponse(List<InformationCenterBean> response) {
                        //mTv.setText(u.toString());
                        offset = 0;
                        if (response.size() > 0) {
//                            mView.showContentPage();
                        } else {
//                            mView.showEmptyPage();
                        }
                        MyLogger.i(">>>>>>>>>>资讯中心：" + response.toString());
                        FindInformationsPresenter.this.informationBeans.clear();
                        FindInformationsPresenter.this.informationBeans.addAll(response);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }

    @Override
    public void getDataMore() {
        offset=offset+pageSize;
        maps.put("offset",offset+"");
        maps.put("pageSize",pageSize+"");
        OkHttpClientManager.postAsyn(URLs.FINDINFORMATIONS, maps,
                new MyResultCallback<List<InformationCenterBean>>() {
                    @Override
                    public void onError(Request request, String info,Exception e) {
                        offset=offset-pageSize;
                        if (TextUtils.isEmpty(info)) {
                            mView.showToast(R.string.networkerror);
                            e.printStackTrace();
                        } else {
                            mView.showToast(info);
                        }
                    }

                    @Override
                    public void onResponse(List<InformationCenterBean> response) {
                        //mTv.setText(u.toString());
                        MyLogger.i(response.toString());
                        if (response.size()==0){
                            mView.showToast(R.string.islastpage);
                            return;
                        }
                        FindInformationsPresenter.this.informationBeans.addAll(response);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }

    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
        void onHttpResult();
    }

    public FindInformationsPresenter(OnHttpResultListener view) {
        super(view);
    }

    public void initAdapter(Context context, int resource) {

    }

    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            if(informationBeans.size()==0) {//防止下拉刷新和对话框进度同时出现
                //mView.showProgress(false, mView.getStringbyid(R.string.networkrequest));
//                mView.showLoadingPage();
            }
        }

        @Override
        public void onAfter() {
            super.onAfter();
            //mView.hideProgress();
            mView.onHttpResult();
        }
    }

}
