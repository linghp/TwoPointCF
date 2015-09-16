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
    private String[] published_statuss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project);
    }

    @Override
    protected void initData() {
        published_statuss=this.getResources().getStringArray(R.array.published_status);
        generateData();
        followProjectAdapter = new FollowProjectAdapter(this, investedProjectBeans);
        listView.setAdapter(followProjectAdapter);
    }

    private void generateData() {
        for (String published_status : published_statuss) {
            investedProjectBeans.add(new InvestedProjectBean(R.mipmap.raw_1433491802, published_status, "最惠宝", "预计将在2天后审核通过"));
        }
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
        //PublishProjectActivity.startThisActivity(true,this);
        MyPublishDetailActivity.startThisActivity(investedProjectBeans.get(position).getTitle(),investedProjectBeans.get(position).getTime(),this);
    }

    @Override
    public void onClick(View v) {

    }
}
