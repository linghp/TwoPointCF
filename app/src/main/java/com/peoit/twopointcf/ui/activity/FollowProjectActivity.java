package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.InvestedProjectBean;
import com.peoit.twopointcf.ui.adapter.FollowProjectAdapter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注项目
 */
public class FollowProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener,AbPullToRefreshView.OnHeaderRefreshListener {

    private ListView listView;
    private List<InvestedProjectBean> investedProjectBeans = new ArrayList<>();
    private FollowProjectAdapter followProjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project);
    }

    @Override
    protected void initData() {
        generateData();
        followProjectAdapter = new FollowProjectAdapter(this, investedProjectBeans);
        listView.setAdapter(followProjectAdapter);
    }

    private void generateData() {
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.weihengshengwu,"预热中", "伟恒生物", "国内首家可大规模生产药媒用脱酚棉籽蛋白的企业"));
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.hongpa, "预热中", "轰趴", "国内第一家高品质厨师上门服务O2O平台"));
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.shuilongtou, "筹资中", "水龙头安全套", "家用净水神器，5秒就能搞定安装的净水器（第二期）"));
    }
    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
        titleView.setTitle(getString(R.string.title_activity_follow_project));
        listView.setOnItemClickListener(this);

        pullview.setOnHeaderRefreshListener(this);
        pullview.setLoadMoreEnable(false);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InvestFindDetailActivity.startThisActivity(null,this);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {

    }
}
