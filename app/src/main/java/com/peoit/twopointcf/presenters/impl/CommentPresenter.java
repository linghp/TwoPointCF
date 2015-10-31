package com.peoit.twopointcf.presenters.impl;

import android.text.TextUtils;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.BasePresenter;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.entity.CommentBean;
import com.peoit.twopointcf.net.OkHttpClientManager;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.interfaces.IComment;
import com.peoit.twopointcf.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/10/28.
 * description:评论
 */
public class CommentPresenter extends BasePresenter<CommentPresenter.OnHttpResultListener> implements IComment {
    private List<CommentBean> commentBeans;
    private Map maps;
    private int offset = 1;
    private int pageSize = 10;

    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }

    public CommentPresenter(OnHttpResultListener view, List<CommentBean> commentBeans) {
        super(view);
        this.commentBeans = commentBeans;
    }

    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            mView.showLoadingPage();
        }

        @Override
        public void onAfter() {
            super.onAfter();
        }
    }

    public abstract class MyResultCallback_PublishComment<T> extends OkHttpClientManager.ResultCallback<T> {

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
    public void getData(Map maps, final boolean isMore) {
        this.maps = maps;
        if (!isMore) {
            offset = 1;
        }
        maps.put("offset", offset+"");
        maps.put("pageSize", pageSize + "");
        //MyLogger.i(">>>>>>>>>>>请求项目，传入的参数" + maps);
        OkHttpClientManager.postAsyn(URLs.LISTCOMMENTS, maps,
                new MyResultCallback<List<CommentBean>>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        if(isMore){
                            offset--;
                        }
                        if (TextUtils.isEmpty(info)) {
                            mView.showToast(R.string.networkerror);
                            e.printStackTrace();
                        } else {
                            mView.showToast(info);
                        }
                        mView.showErrorPage();
                    }

                    @Override
                    public void onResponse(List<CommentBean> response) {
                        //mTv.setText(u.toString());
                        if (response.size() > 0) {
                            mView.showContentPage();
                        } else {
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
                        MyLogger.i(">>>>>>>>>>请求项目：" + response.toString());
                        if (!isMore) {
                            CommentPresenter.this.commentBeans.clear();
                        }
                            offset++;
                        CommentPresenter.this.commentBeans.addAll(response);
                        mView.onHttpResultSuccess();
                    }
                }, mView);
    }

    @Override
    public void publishComment(Map map) {
        //MyLogger.i(">>>>>>>>>>>请求项目，传入的参数" + map);
        OkHttpClientManager.postAsyn(URLs.PUBLISHCOMMENT, map, new MyResultCallback_PublishComment<Object>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (TextUtils.isEmpty(info)) {
                    mView.showToast(R.string.networkerror);
                    e.printStackTrace();
                } else {
                    mView.showToast(R.string.commentfail);
                }
            }

            @Override
            public void onResponse(Object response) {
                mView.showToast(R.string.commentsuccess);
                getData(maps, false);
            }
        });
    }
}
