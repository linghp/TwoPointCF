package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.FindProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.IFindProject;
import com.peoit.twopointcf.ui.adapter.InvestedProjectAdapter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.List;

public class InvestedProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener,FindProjectPresenter.OnHttpResultListener,
        AbPullToRefreshView.OnFooterLoadListener,AbPullToRefreshView.OnHeaderRefreshListener{
    private ListView listView;
    private List<ProjectBean> investedProjectBeans = new ArrayList<>();
    private InvestedProjectAdapter investedProjectAdapter;
    private IFindProject presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project);
    }

    @Override
    protected void initData() {
        presenter=new FindProjectPresenter(this);
        params.put("investorId",localUserInfo.getUserId());
        presenter.getData(URLs.FINDINVESTEDPROJECT,params, investedProjectBeans);
        investedProjectAdapter = new InvestedProjectAdapter(this, investedProjectBeans);
        listView.setAdapter(investedProjectAdapter);
    }

//    private void generateData() {
//        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.weihengshengwu, "2015-06-30", "最惠宝", "20万元"));
//        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.hongpa, "2015-06-30", "轰趴", "20万元"));
//        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.shuilongtou, "2015-06-30", "水龙头安全套", "20万元"));
//    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
        titleView.setTitle(getString(R.string.title_activity_invested_project));
        listView.setOnItemClickListener(this);
        pullview.setOnHeaderRefreshListener(this);
        pullview.setOnFooterLoadListener(this);
    }

    @Override
    protected void updateView() {


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InvestedProjectDetailActivity.startThisActivity(investedProjectBeans.get(position).projectName, this);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        presenter.getData(URLs.FINDINVESTEDPROJECT,params, investedProjectBeans);
    }

    @Override
    public void onHttpResultSuccess() {
        investedProjectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpResult() {
        pullview.onHeaderRefreshFinish();
        pullview.onFooterLoadFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getDataMore(URLs.FINDINVESTEDPROJECT,params);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(URLs.FINDINVESTEDPROJECT,params,investedProjectBeans);
    }
}
