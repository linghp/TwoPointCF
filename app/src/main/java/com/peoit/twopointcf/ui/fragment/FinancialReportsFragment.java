package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.BusinessDynamicsBean;
import com.peoit.twopointcf.entity.FinancialReportsBean;
import com.peoit.twopointcf.ui.adapter.FinancialReportsFragmentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/9/1.
 * description:（我的项目->经营管理->)财务报告
 */
public class FinancialReportsFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private ListView listView;
    private List<FinancialReportsBean> lists;
    private FinancialReportsFragmentAdapter financialReportsFragmentAdapter;

    public FinancialReportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_financialreports, container, false);
    }


    @Override
    protected void initView(View view) {
        listView= (ListView) view.findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        lists=new ArrayList<>();
        generatedata();
        financialReportsFragmentAdapter=new FinancialReportsFragmentAdapter(getActivity(),lists);
        listView.setAdapter(financialReportsFragmentAdapter);
        listView.setOnItemClickListener(this);
    }

    private void generatedata() {
        lists.add(new FinancialReportsBean(FinancialReportsBean.SECTION,"8月","","",""));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"5号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"12号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"16号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"25号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(FinancialReportsBean.SECTION,"7月","","",""));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"3号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM, "12号", "13500", "108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"16号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"25号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"27号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"31号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(FinancialReportsBean.SECTION,"6月","","",""));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"3号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"5号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"6号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"8号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM, "12号", "13500", "108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"16号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"25号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"27号","13500","108888","备注"));
        lists.add(new FinancialReportsBean(BusinessDynamicsBean.ITEM,"30号","13500","108888","备注"));
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //CommonUtil.gotoActivity(getActivity(), BusinessManagerDetailActivity.class,false);
    }
}
