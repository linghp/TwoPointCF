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
import com.peoit.twopointcf.ui.adapter.ProjectAdapter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPublishProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener,FindProjectPresenter.OnHttpResultListener,
        AbPullToRefreshView.OnFooterLoadListener,AbPullToRefreshView.OnHeaderRefreshListener{
    private ListView listView;
    private List<ProjectBean> investedProjectBeans = new ArrayList<>();
    private ProjectAdapter projectAdapter;
    private String[] published_statuss;
    private IFindProject presenter;
    private Map<String, String> maps = new HashMap<>();
    public static Map<String, String> maps_status = new HashMap<>();
    public static final String WAITING_INVESTED="waiting_invested";

    static {
        maps_status.put("waiting_verified","待审核");
        maps_status.put("waiting_pay","待付保证金");
        maps_status.put(WAITING_INVESTED,"众筹中");
        maps_status.put("verify_failed","驳回");
        maps_status.put("invest_failed","众筹失败");
        maps_status.put("invest_delay","待延期审核");
        maps_status.put("invest_success","待启动");
        maps_status.put("project_success","众筹成功");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project);
    }

    @Override
    protected void initData() {
        presenter=new FindProjectPresenter(this);
        published_statuss=this.getResources().getStringArray(R.array.published_status);
        //generateData();
        projectAdapter = new ProjectAdapter(this, investedProjectBeans,maps_status);
        listView.setAdapter(projectAdapter);

        maps.put("publisherId", localUserInfo.getUserId());
        presenter.getData(URLs.FINDPROJECT,maps, investedProjectBeans);
    }

//    private void generateData() {
//        for (String published_status : published_statuss) {
//            investedProjectBeans.add(new InvestedProjectBean(R.mipmap.raw_1433491802, published_status, "最惠宝", "预计将在2天后审核通过"));
//        }
//    }
    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
        titleView.setTitle(getString(R.string.title_activity_my_publish_project));
        listView.setOnItemClickListener(this);
        pullview.setOnHeaderRefreshListener(this);
        pullview.setOnFooterLoadListener(this);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //PublishProjectActivity.startThisActivity(true,this);
        MyPublishDetailActivity.startThisActivity(investedProjectBeans.get(position).projectName,investedProjectBeans.get(position).status,this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        presenter.getData(URLs.FINDPROJECT,maps, investedProjectBeans);
    }

    @Override
    public void onHttpResultSuccess() {
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpResult() {
        pullview.onHeaderRefreshFinish();
        pullview.onFooterLoadFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getDataMore(URLs.FINDPROJECT,maps);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(URLs.FINDPROJECT,maps,investedProjectBeans);
    }
}
