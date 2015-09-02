package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.InvestedProjectBean;
import com.peoit.twopointcf.ui.adapter.InvestedProjectAdapter;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class InvestedProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private List<InvestedProjectBean> investedProjectBeans = new ArrayList<>();
    private InvestedProjectAdapter investedProjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project);
    }

    @Override
    protected void initData() {
        generateData();
        investedProjectAdapter = new InvestedProjectAdapter(this, investedProjectBeans);
        listView.setAdapter(investedProjectAdapter);
    }

    private void generateData() {
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.weihengshengwu, "2015-06-30", "最惠宝", "20万元"));
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.hongpa, "2015-06-30", "轰趴", "20万元"));
        investedProjectBeans.add(new InvestedProjectBean(R.mipmap.shuilongtou, "2015-06-30", "水龙头安全套", "20万元"));
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
        titleView.setTitle(getString(R.string.title_activity_invested_project));
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InvestedProjectDetailActivity.startThisActivity(investedProjectBeans.get(position).getTitle(), this);
    }
}
