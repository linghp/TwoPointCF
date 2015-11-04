package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.FindProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.IFindProject;
import com.peoit.twopointcf.ui.activity.BusinessManagerActivity;
import com.peoit.twopointcf.ui.activity.BusinessManagerDetailActivity;
import com.peoit.twopointcf.ui.adapter.BusinessManagerAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/8/31.
 * description:（我的项目->）经营管理fragment
 */
public class BusinessManagerFragment extends BaseFragment implements AdapterView.OnItemClickListener, FindProjectPresenter.OnHttpResultListener,
        AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener {
    private ListView listView;
    private List<ProjectBean> lists = new ArrayList<>();
    private BusinessManagerAdapter businessManagerAdapter;
    private String type;
    private IFindProject presenter;
    private Map params = new HashMap();

    public BusinessManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_invested_project, container, false);
    }


    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        pullview.setOnHeaderRefreshListener(this);
        pullview.setOnFooterLoadListener(this);
    }

    @Override
    protected void initData() {
        type = getArguments().getString("type");
        presenter = new FindProjectPresenter(this);

        switch (type) {
            case BusinessManagerActivity.INVESTED:
                params.put("investorId", localUserInfo.getUserId());
                requestServer();
                break;
            case BusinessManagerActivity.PUBLISHED:
                params.put("publisherId", localUserInfo.getUserId());
                params.put("status", "project_success");//众筹成功的项目
                requestServer();
                break;
        }
        businessManagerAdapter = new BusinessManagerAdapter(getActivity(), R.layout.item_businessmanager, lists);
        listView.setAdapter(businessManagerAdapter);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (type) {
            case BusinessManagerActivity.INVESTED:
                //跳转已投项目详情
                BusinessManagerDetailActivity.startThisActivity(false, lists.get(position).id, lists.get(position).projectName, getActivity());
                break;
            case BusinessManagerActivity.PUBLISHED:
                //跳转已发项目详情
                BusinessManagerDetailActivity.startThisActivity(true, lists.get(position).id, lists.get(position).projectName, getActivity());
                break;
        }

    }

    @Override
    public void requestServer() {
        super.requestServer();
        switch (type) {
            case BusinessManagerActivity.INVESTED:
                presenter.getData(URLs.FINDINVESTEDSUCCESSPROJECT, params, lists);
                break;
            case BusinessManagerActivity.PUBLISHED:
                presenter.getData(URLs.FINDPROJECT, params, lists);
                break;
        }
    }

    @Override
    public void onHttpResultSuccess() {
        businessManagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpResult() {
        pullview.onHeaderRefreshFinish();
        pullview.onFooterLoadFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        switch (type) {
            case BusinessManagerActivity.INVESTED:
                presenter.getDataMore(URLs.FINDINVESTEDPROJECT, params);
                break;
            case BusinessManagerActivity.PUBLISHED:
                presenter.getDataMore(URLs.FINDPROJECT, params);
                break;
        }
    }


    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        switch (type) {
            case BusinessManagerActivity.INVESTED:
                presenter.getData(URLs.FINDINVESTEDPROJECT, params, lists);
                break;
            case BusinessManagerActivity.PUBLISHED:
                presenter.getData(URLs.FINDPROJECT, params, lists);
                break;
        }
    }
}
