package com.peoit.twopointcf.presenters.impl;

import android.content.Context;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IFindProject;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/9/7.
 * description:
 */
public class FindProjectPresenter extends BasePresenter<FindProjectPresenter.OnHttpResultListener> implements IFindProject {
    private List<ProjectBean> projectBeans;
    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
        void onHttpResult();
    }

    public FindProjectPresenter(OnHttpResultListener view) {
        super(view);
    }

    public void initAdapter(Context context, int resource) {

    }

    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            if(projectBeans.size()==0) {//防止下拉刷新和对话框进度同时出现
                mView.showProgress(false, mView.getStringbyid(R.string.networkrequest));
            }
        }

        @Override
        public void onAfter() {
            super.onAfter();
            mView.hideProgress();
            mView.onHttpResult();
        }
    }

    @Override
    public void getData(Map maps,List<ProjectBean> projectBeans) {
        this.projectBeans=projectBeans;
        OkHttpClientManager.postAsyn(URLs.FINDPROJECT, maps,
                new MyResultCallback<List<ProjectBean>>() {
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
                    public void onResponse(List<ProjectBean> response) {
                        //mTv.setText(u.toString());
                        MyLogger.i(response.toString());
                        FindProjectPresenter.this.projectBeans.clear();
                        FindProjectPresenter.this.projectBeans.addAll(response);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }
}
