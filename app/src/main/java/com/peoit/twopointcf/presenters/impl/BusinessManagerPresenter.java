package com.peoit.twopointcf.presenters.impl;

import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.FinancialReportsBean;
import com.peoit.twopointcf.entity.ProjectAnnouncementBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.presenters.interfaces.IBusinessManager;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.List;
import java.util.Map;

/**
 * 经营管理数据请求类
 * Created by zyz on 2015/10/28.
 */
public class BusinessManagerPresenter extends BasePresenter<BusinessManagerPresenter.OnHttpResultListener> implements IBusinessManager {
    private List<ProjectAnnouncementBean> projectAnnouncementBeans;

    private List<FinancialReportsBean> financialReportsBeans;

    public BusinessManagerPresenter(OnHttpResultListener view) {
        super(view);
    }

    public void setProjectAnnouncementBeans(List<ProjectAnnouncementBean> projectAnnouncementBeans) {
        this.projectAnnouncementBeans = projectAnnouncementBeans;
    }

    public void setFinancialReportsBeans(List<FinancialReportsBean> financialReportsBeans) {
        this.financialReportsBeans = financialReportsBeans;
    }


    @Override
    public void getData(String url, Map map) {
        OkHttpClientManager.postAsyn(url, map, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("请求失败" + info);
                    MyLogger.i(">>>>请求失败>>>>" + info);
                }
            }

            @Override
            public void onResponse(Object response) {
                mView.showToast("提交成功");
                if (response != null)
                    MyLogger.i(">>>>返回对象数据>>>>" + response);
                mView.onHttpResultSuccess();
                //((Activity) mView).finish();
            }
        });
    }

    /**
     * 弃用
     *
     * @param url
     * @param map
     */
    @Override
    public void getData_List(String url, Map map) {
        OkHttpClientManager.postAsyn(url, map, new MyResultCallback<List<ProjectAnnouncementBean>>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("请求失败" + info);
                }
            }

            @Override
            public void onResponse(List<ProjectAnnouncementBean> response) {
                mView.showToast("提交成功");
                MyLogger.i(">>>>返回对象数据>>>>" + response);
                projectAnnouncementBeans.clear();
                projectAnnouncementBeans.addAll(response);
                mView.onHttpResultSuccess();
                //((Activity) mView).finish();
            }
        });
    }

    /**
     * 获取项目公告
     *
     * @param url
     * @param map
     */
    public void getListNotice(String url, Map map) {
        OkHttpClientManager.postAsyn(url, map, new MyResultCallback<List<ProjectAnnouncementBean>>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("请求失败" + info);
                }
            }

            @Override
            public void onResponse(List<ProjectAnnouncementBean> response) {
                MyLogger.i(">>>>返回对象数据>>>>" + response);
                projectAnnouncementBeans.clear();
                projectAnnouncementBeans.addAll(response);
                mView.onHttpResultSuccess();
                //((Activity) mView).finish();
            }
        });
    }

    /**
     * 获取财务报表
     *
     * @param url
     * @param map
     */
    public void getListReport(String url, Map map) {
        OkHttpClientManager.postAsyn(url, map, new MyResultCallback<List<FinancialReportsBean>>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("请求失败" + info);
                }
            }

            @Override
            public void onResponse(List<FinancialReportsBean> response) {
                MyLogger.i(">>>>返回对象数据>>>>" + response);
                financialReportsBeans.clear();
                financialReportsBeans.addAll(response);
                mView.onHttpResultSuccess();
                //((Activity) mView).finish();
            }
        });
    }

    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
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
