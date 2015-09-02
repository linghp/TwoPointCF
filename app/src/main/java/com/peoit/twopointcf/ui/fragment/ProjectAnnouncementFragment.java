package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectAnnouncementBean;
import com.peoit.twopointcf.ui.adapter.ProjectAnnouncementAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/9/1.
 * description:（我的项目->经营管理->)项目公告
 */
public class ProjectAnnouncementFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private ListView listView;
    private List<ProjectAnnouncementBean> lists;
    private ProjectAnnouncementAdapter projectAnnouncementAdapter;

    public ProjectAnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.common_list, container, false);
    }


    @Override
    protected void initView(View view) {
        listView= (ListView) view.findViewById(R.id.listview);
    }

    @Override
    protected void initData() {
        lists=new ArrayList<>();
        generatedata();
        projectAnnouncementAdapter=new ProjectAnnouncementAdapter(getActivity(),R.layout.item_projectannouncement,lists);
        listView.setAdapter(projectAnnouncementAdapter);
        listView.setOnItemClickListener(this);
    }

    private void generatedata() {
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的","2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的上的发生的水电费水电费和好的方法的符合地方好地方很多地方的法规的法规","2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的是滴是滴","2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的少的地方是是滴是滴","2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费","2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费","2015/08/12"));
        lists.add(new ProjectAnnouncementBean("发生的所得税的所得税的水电费是滴是滴所得税的树干上的水电费收水电费水电费的所发生的所得税的树干上的水电费","2015/08/12"));
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //CommonUtil.gotoActivity(getActivity(), BusinessManagerDetailActivity.class,false);
    }
}
