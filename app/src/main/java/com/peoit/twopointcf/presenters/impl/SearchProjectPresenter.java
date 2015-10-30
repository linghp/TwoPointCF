package com.peoit.twopointcf.presenters.impl;

import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.ISearchProject;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/10/28.
 * description:搜索
 */
public class SearchProjectPresenter extends BasePresenter<SearchProjectPresenter.OnHttpResultListener> implements ISearchProject {
    private List<ProjectBean> projectBeans;
    private int offset = 1;
    private int pageSize = 10;

    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
        void onHttpResult();
    }

    public SearchProjectPresenter(OnHttpResultListener view,List<ProjectBean> projectBeans) {
        super(view);
        this.projectBeans = projectBeans;
    }


    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            if(projectBeans.size()==0) {//防止上拉刷新和对话框进度同时出现
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
    public void getData(Map maps, final boolean isMore) {
        if (!isMore){
            SearchProjectPresenter.this.projectBeans.clear();
            offset=1;
        }
        maps.put("offset", offset + "");
        maps.put("pageSize", pageSize + "");
        //MyLogger.i(">>>>>>>>>>>请求项目，传入的参数" + maps);
        OkHttpClientManager.postAsyn(URLs.FINDPROJECT, maps,
                new MyResultCallback<List<ProjectBean>>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        if (!isMore){
                            offset--;
                        }
                        if (TextUtils.isEmpty(info)) {
                            mView.showToast(R.string.networkerror);
                            e.printStackTrace();
                        } else {
                            mView.showToast(info);
                        }
                    }

                    @Override
                    public void onResponse(List<ProjectBean> response) {
                        MyLogger.i(response.toString());
                        if (isMore){
                            offset++;
                        }
                        if (isMore&&response.size()==0){
                            mView.showToast(R.string.islastpage);
                            return;
                        }
                        if(!isMore&&response.size()==0){
                            mView.showToast(R.string.nodata);
                        }
                        SearchProjectPresenter.this.projectBeans.addAll(response);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }
}
