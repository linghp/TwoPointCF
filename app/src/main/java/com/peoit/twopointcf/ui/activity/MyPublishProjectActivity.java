package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.InvestedProjectBean;
import com.peoit.twopointcf.ui.adapter.FollowProjectAdapter;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MyPublishProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener{
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
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.raw_1433491802, R.mipmap.passed, "最惠宝", "预计将在2天后审核通过"));
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.hongpa, R.mipmap.verifying, "轰趴", "预计将在2天后审核通过"));
    }
    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
        titleView.setTitle(getString(R.string.title_activity_my_publish_project));
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PublishProjectActivity.startThisActivity(true,this);
    }
}
