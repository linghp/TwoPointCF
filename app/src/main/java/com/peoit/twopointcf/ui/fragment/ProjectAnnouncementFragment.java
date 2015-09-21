package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectAnnouncementBean;
import com.peoit.twopointcf.other.ScrollAwareFABBehavior;
import com.peoit.twopointcf.ui.adapter.ProjectAnnouncementAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/9/1.
 * description:（我的项目->经营管理->)项目公告
 */
public class ProjectAnnouncementFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private List<ProjectAnnouncementBean> lists;
    private ProjectAnnouncementAdapter projectAnnouncementAdapter;
    private boolean isPublished=true;

    public ProjectAnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.common_recyclerview_fab, container, false);
    }


    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) view.findViewById(R.id.fabBtn);
        // 如果布局大小一致有利于优化
        recyclerView.setHasFixedSize(true);
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initData() {
        lists = new ArrayList<>();
        generatedata();
        projectAnnouncementAdapter = new ProjectAnnouncementAdapter(getActivity(), lists);
        recyclerView.setAdapter(projectAnnouncementAdapter);
        //recyclerView.setOnItemClickListener(this);
    }

    private void generatedata() {
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的上的发生的水电费水电费和好的方法的符合地方好地方很多地方的法规的法规", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的是滴是滴", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的少的地方是是滴是滴", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费", "2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费", "2015/08/12"));
    }

    @Override
    protected void updateView() {
        if (!isPublished) {
            fab.setVisibility(View.GONE);
            ScrollAwareFABBehavior.isdisplay=false;
        }else {
            ScrollAwareFABBehavior.isdisplay=true;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //CommonUtil.gotoActivity(getActivity(), BusinessManagerDetailActivity.class,false);
    }

    public void hiddenFAB() {
        isPublished = false;
    }

}
