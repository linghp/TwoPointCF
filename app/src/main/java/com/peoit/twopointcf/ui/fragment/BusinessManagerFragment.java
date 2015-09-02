package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.BusinessManagerBean;
import com.peoit.twopointcf.ui.activity.BusinessManagerActivity;
import com.peoit.twopointcf.ui.activity.BusinessManagerDetailActivity;
import com.peoit.twopointcf.ui.adapter.BusinessManagerAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/8/31.
 * description:（我的项目->）经营管理fragment
 */
public class BusinessManagerFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private ListView listView;
    private List<BusinessManagerBean> lists;
    private BusinessManagerAdapter businessManagerAdapter;
    private String type;

    public BusinessManagerFragment() {
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
        type=getArguments().getString("type");

        lists=new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lists.add(new BusinessManagerBean());
        }
        businessManagerAdapter=new BusinessManagerAdapter(getActivity(),R.layout.item_businessmanager,lists);
        listView.setAdapter(businessManagerAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (type) {
            case BusinessManagerActivity.INVESTED:
                CommonUtil.gotoActivity(getActivity(), BusinessManagerDetailActivity.class,false);
                break;
            case BusinessManagerActivity.PUBLISHED:
                BusinessManagerDetailActivity.startThisActivity(true,getActivity());
                break;
        }

    }
}
