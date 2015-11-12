package com.peoit.twopointcf.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.CommentBean;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.presenters.impl.CommentPresenter;
import com.peoit.twopointcf.presenters.interfaces.IComment;
import com.peoit.twopointcf.ui.activity.InvestFindDetailActivity;
import com.peoit.twopointcf.ui.activity.LoginActivity;
import com.peoit.twopointcf.ui.adapter.CommentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.view.NoScrollListView;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/8/7.
 * last:2015/8/7
 * description:
 */
public class InvestFindDetailSub4Fragment extends BaseFragment implements CommentPresenter.OnHttpResultListener {
    private NoScrollListView listView;
    private EditText et_comment;
    private View bt_send;
    private CommentAdapter commentAdapter;
    private List<CommentBean> items = new ArrayList<>();
    private IComment presenter;
    private ProjectBean projectBean;

    public InvestFindDetailSub4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest_find_detail_sub4, container, false);
    }


    @Override
    protected void initView(View view) {
        listView = findViewByID_My(R.id.listView);
        et_comment = findViewByID_My(R.id.et_comment);
        bt_send = findViewByID_My(R.id.bt_send);
        bt_send.setOnClickListener(this);
        et_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    // 先隐藏键盘'
                    ((InputMethodManager) et_comment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    mActivity
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    String comment = v.getText().toString().trim();
                    if (TextUtils.isEmpty(comment)) {
                        myToast("评论不能为空");
                    } else {
                        if (projectBean != null) {
                            maps.clear();
                            maps.put("projectId", projectBean.id);
                            maps.put("discussantId", projectBean.id);
                            maps.put("content", v.getText().toString().trim());
                            presenter.publishComment(maps);
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new CommentPresenter(this, items);
        commentAdapter = new CommentAdapter(mActivity, items);
        listView.setAdapter(commentAdapter);

        projectBean = ((InvestFindDetailActivity) getActivity()).projectBean;
        if (projectBean != null) {
            maps.put("projectId", projectBean.id);
            requestServer();
        }

        //listView.setParentScrollView(((InvestFindDetailActivity)getActivity()).myScrollView);
    }

    @Override
    protected void updateView() {
        if(projectBean.isfinish) {
            View view_place = findViewByID_My(R.id.view_place);
            if (view_place != null) {
                view_place.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
        presenter.getData(maps, false);
    }

    @Override
    public void onHttpResultSuccess() {
        commentAdapter.notifyDataSetChanged();
        et_comment.setText("");
        et_comment.clearFocus();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_send:
                if (!localUserInfo.isLogin()) {
                    CommonUtil.gotoActivity(mActivity, LoginActivity.class, false);
                    return;
                }
                String comment = et_comment.getText().toString().trim();
                if (TextUtils.isEmpty(comment)) {
                    myToast("评论不能为空");
                } else {
                    if (projectBean != null) {
                        Map<String, String> maps = new HashMap<>();
                        maps.put("projectId", projectBean.id);
                        maps.put("discussantId", localUserInfo.getUserId());
                        maps.put("content", comment);
                        presenter.publishComment(maps);
                    }
                }
                break;
        }
    }
}
