package com.peoit.twopointcf.presenters.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.IsConcernBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IProjectDetail;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * 项目详情操作
 * Created by zyz on 2015/9/24.
 */
public class ProjectDetailPresenter extends BasePresenter<ProjectDetailPresenter.OnHttpResultListener> implements IProjectDetail {

    public ProjectDetailPresenter(OnHttpResultListener view) {
        super(view);
    }

    @Override
    public void getData(Map maps) {

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

    /**
     * 关注项目
     * @param maps
     * @param strings
     */
    public void getConcernProject(Map maps, final onCancelProject strings) {
        OkHttpClientManager.postAsyn(URLs.CONCERNPROJECT, maps, new MyResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("关注失败");
                }
            }

            @Override
            public void onResponse(String response) {
                if (response != null) {
                    MyLogger.i(">>>>>>>>>>>>>>>>关注项目" + response);
                    if (strings != null) {
                        strings.onSueccess("true");
                    }
                }
            }
        }, mView);
    }

    /**
     * 判断用户是否关注项目
     *
     * @param maps
     * @param isConcern
     */
    public void getIsConcern(Map maps, final onIsConcern isConcern) {
        OkHttpClientManager.postAsyn(URLs.ISCANCELPROJECT, maps, new MyResultCallback<IsConcernBean>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
//                    mView.showToast("获取关注状态失败");
                }
            }

            @Override
            public void onResponse(IsConcernBean response) {
                if (response != null) {
                    MyLogger.i(">>>>>>>>>>>>>>>>是否关注项目" + response);
                    if (isConcern != null) {
                        isConcern.onSueccess(response);
                    }
                }
            }
        }, mView);
    }

    public interface onCancelProject {
        void onSueccess(String bean);
    }

    public interface onIsConcern {
        void onSueccess(IsConcernBean bean);
    }

    /**
     * 支付
     * @param maps
     */
    @Override
    public void payMargin(Map maps) {
        OkHttpClientManager.postAsyn(URLs.PAYMARGIN, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("支付失败");
                }
            }

            @Override
            public void onResponse(Object response) {
                if (response != null) {
                    MyLogger.i(">>>>>>>>>>>>>>>>支付" + response);
                }
                mView.showToast("支付成功");
                ((Activity) mView).finish();
            }
        }, mView);
    }

    /**
     * 取消项目
     */
    public void getCancelProject(Map maps) {
        OkHttpClientManager.postAsyn(URLs.CANCELPROJECT, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("取消失败");
                }
            }

            @Override
            public void onResponse(Object response) {
                if (response != null) {
                    MyLogger.i(">>>>>>>>>>>>>>>>取消项目" + response);
                }
                mView.showToast("取消成功");
                ((Activity) mView).finish();
            }
        }, mView);
    }
    /**
     * 启动项目
     */
    public void getStartProject(Map maps) {
        OkHttpClientManager.postAsyn(URLs.STARTPROJECT, maps, new MyResultCallback<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("启动失败");
                }
            }

            @Override
            public void onResponse(Object response) {
                if (response != null) {
                    MyLogger.i(">>>>>>>>>>>>>>>>启动项目" + response);
                }
                mView.showToast("启动成功");
                ((Activity) mView).finish();
            }
        }, mView);
    }
    /**
     *
     */

}
