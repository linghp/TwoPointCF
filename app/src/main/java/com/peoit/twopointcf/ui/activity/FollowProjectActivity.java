package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.FindProjectPresenter;
import com.peoit.twopointcf.ui.adapter.ProjectAdapter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关注项目
 */
public class FollowProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener,FindProjectPresenter.OnHttpResultListener,
        AbPullToRefreshView.OnFooterLoadListener,AbPullToRefreshView.OnHeaderRefreshListener{

    private ListView listView;
    private List<ProjectBean> investedProjectBeans = new ArrayList<>();
//    private FollowProjectAdapter followProjectAdapter;
    private ProjectAdapter investedProjectAdapter;
    public static Map<String, String> maps_status = new HashMap<>();
    public static final String WAITING_PAY="waiting_pay";
    public static final String WAITING_INVESTED="waiting_invested";
    public static final String VERIFY_FAILED="verify_failed";
    public static final String INVEST_FAILED="invest_failed";
    public static final String INVEST_SUCCESS="invest_success";
    public static final String PROJECT_SUCCESS="project_success";

    static {
        maps_status.put("waiting_verified","待审核");
        maps_status.put(WAITING_PAY,"待付保证金");
        maps_status.put(WAITING_INVESTED,"众筹中");
        maps_status.put(VERIFY_FAILED,"驳回");
        maps_status.put(INVEST_FAILED,"众筹失败");
        maps_status.put("invest_delay","待延期审核");
        maps_status.put(INVEST_SUCCESS,"待启动");
        maps_status.put(PROJECT_SUCCESS,"众筹成功");
    }

    private FindProjectPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project);
    }

    @Override
    protected void initData() {
        presenter=new FindProjectPresenter(this);
//        generateData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        params.put("attentionId", localUserInfo.getUserId());
        presenter.getData(URLs.FINDCONCERNEDPROJECT, params, investedProjectBeans);
//        followProjectAdapter = new FollowProjectAdapter(FollowProjectActivity.this, investedProjectBeans);
        investedProjectAdapter = new ProjectAdapter(this, investedProjectBeans,maps_status);
        listView.setAdapter(investedProjectAdapter);
    }

    /* private void generateData() {
            investedProjectBeans.add(new InvestedProjectBean(R.mipmap.weihengshengwu,"预热中", "伟恒生物", "国内首家可大规模生产药媒用脱酚棉籽蛋白的企业"));
            investedProjectBeans.add(new InvestedProjectBean(R.mipmap.hongpa, "预热中", "轰趴", "国内第一家高品质厨师上门服务O2O平台"));
            investedProjectBeans.add(new InvestedProjectBean(R.mipmap.shuilongtou, "筹资中", "水龙头安全套", "家用净水神器，5秒就能搞定安装的净水器（第二期）"));
        }*/
    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
        titleView.setTitle(getString(R.string.title_activity_follow_project));
        listView.setOnItemClickListener(this);
        pullview.setOnHeaderRefreshListener(this);
        pullview.setOnFooterLoadListener(this);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        InvestFindDetailActivity.startThisActivity(null,false,this);
        InvestFindDetailActivity.startThisActivity(investedProjectBeans.get(position),-1,this);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        presenter.getData(URLs.FINDCONCERNEDPROJECT, params, investedProjectBeans);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(URLs.FINDCONCERNEDPROJECT, params, investedProjectBeans);
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
        presenter.getDataMore(URLs.FINDCONCERNEDPROJECT,params);
    }
}
