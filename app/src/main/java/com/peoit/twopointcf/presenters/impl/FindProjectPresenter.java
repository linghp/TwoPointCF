package com.peoit.twopointcf.presenters.impl;

import android.content.Context;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.BannerBean;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.presenters.interfaces.IFindProject;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/9/7.
 * description:
 */
public class FindProjectPresenter extends BasePresenter<FindProjectPresenter.OnHttpResultListener> implements IFindProject {
    private List<ProjectBean> projectBeans;
    private List<BannerBean> bannerBeans;
    private int offset=0;
    private int pageSize=10;

    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
        void onHttpResult();
    }

    public interface OnHttpResultBannerListener {
        void onHttpResultSuccessBanner();
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
                //mView.showProgress(false, mView.getStringbyid(R.string.networkrequest));
                mView.showLoadingPage();
            }
        }

        @Override
        public void onAfter() {
            super.onAfter();
            //mView.hideProgress();
            mView.onHttpResult();
        }
    }

    @Override
    public void getDataBanner(String url, List<BannerBean> bannerBeans,final OnHttpResultBannerListener listener) {
        this.bannerBeans=bannerBeans;
        OkHttpClientManager.postAsyn(url, new HashMap<String, String>(),
                new OkHttpClientManager.ResultCallback<List<BannerBean>>() {
                    @Override
                    public void onError(Request request, String info,Exception e) {
//                        if (TextUtils.isEmpty(info)) {
//                            mView.showToast(R.string.networkerror);
//                            e.printStackTrace();
//                        } else {
//                            mView.showToast(info);
//                        }
//                        mView.showErrorPage();
                    }

                    @Override
                    public void onResponse(List<BannerBean> response) {
                        MyLogger.i(">>>>>>>>>>请求项目："+response.toString());
                        FindProjectPresenter.this.bannerBeans.clear();
                        FindProjectPresenter.this.bannerBeans.addAll(response);
                        listener.onHttpResultSuccessBanner();
                    }
                }, mView);
    }

    @Override
    public void getData(String url,Map maps,List<ProjectBean> projectBeans) {
        this.projectBeans=projectBeans;
        maps.put("offset",0+"");
        maps.put("pageSize", pageSize + "");
        MyLogger.i(">>>>>>>>>>>请求项目，传入的参数" + maps);
        OkHttpClientManager.postAsyn(url, maps,
                new MyResultCallback<List<ProjectBean>>() {
                    @Override
                    public void onError(Request request, String info,Exception e) {
                        if (TextUtils.isEmpty(info)) {
                            mView.showToast(R.string.networkerror);
                            e.printStackTrace();
                        } else {
                            mView.showToast(info);
                        }
                        mView.showErrorPage();
                    }

                    @Override
                    public void onResponse(List<ProjectBean> response) {
                        //mTv.setText(u.toString());
                        offset=0;
                        if(response.size()>0){
                            mView.showContentPage();
                        }else {
                            mView.showEmptyPage();
                           /* DialogTool.createCommonDialog((Activity)mView, R.mipmap.ic_launcher, "关注项目", "您还没有关注的项目，立即关注吧？", "确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    CommonUtil.gotoActivity((Activity) mView, InvestFindSubFragment.class, false);
                                }
                            }, "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                        myToast("取消");
                                }
                            }).show();*/
                        }
                        MyLogger.i(response.toString());
                        FindProjectPresenter.this.projectBeans.clear();
                        FindProjectPresenter.this.projectBeans.addAll(response);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }

    @Override
    public void getDataMore(String url,Map maps) {
        offset=offset+pageSize;
        maps.put("offset",offset+"");
        maps.put("pageSize",pageSize+"");
        OkHttpClientManager.postAsyn(url, maps,
                new MyResultCallback<List<ProjectBean>>() {
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
                    public void onResponse(List<ProjectBean> response) {
                        //mTv.setText(u.toString());
                        MyLogger.i(response.toString());
                        if (response.size()==0){
                            mView.showToast(R.string.islastpage);
                            return;
                        }
                        FindProjectPresenter.this.projectBeans.addAll(response);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }
}
