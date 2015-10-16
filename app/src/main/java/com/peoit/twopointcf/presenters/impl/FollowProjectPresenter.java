package com.peoit.twopointcf.presenters.impl;

import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.IsConcernBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IFollowProject;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * 关注项目
 * Created by zyz on 2015/9/24.
 */
public class FollowProjectPresenter extends BasePresenter<FollowProjectPresenter.OnHttpResultListener> implements IFollowProject {

    public FollowProjectPresenter(OnHttpResultListener view) {
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

    //
    public void getCancelProject(Map maps, final onCancelProject strings) {
        OkHttpClientManager.postAsyn(URLs.CANCELPROJECT, maps, new MyResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast("请求失败");
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
                    mView.showToast("获取关注状态失败");
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
}
