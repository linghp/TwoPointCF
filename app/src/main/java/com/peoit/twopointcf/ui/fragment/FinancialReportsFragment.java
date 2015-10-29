package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.FinancialReportsBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.BusinessManagerPresenter;
import com.peoit.twopointcf.ui.activity.BusinessManagerDetailActivity;
import com.peoit.twopointcf.ui.adapter.FinancialReportsFragmentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.AbDateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/9/1.
 * description:（我的项目->经营管理->)财务报告
 */
public class FinancialReportsFragment extends BaseFragment implements AdapterView.OnItemClickListener, BusinessManagerPresenter.OnHttpResultListener {
    private ListView listView;
    private List<FinancialReportsBean> lists = new ArrayList<>();
    private FinancialReportsFragmentAdapter financialReportsFragmentAdapter;
    private BusinessManagerPresenter presenter;

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
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        presenter = new BusinessManagerPresenter(this);
        presenter.setFinancialReportsBeans(lists);
        requestServer();

//        generatedata();
        financialReportsFragmentAdapter = new FinancialReportsFragmentAdapter(getActivity(), lists);
        listView.setAdapter(financialReportsFragmentAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        Map map = new HashMap();
//        map.put("userId", localUserInfo.getUserId());
        map.put("projectId", ((BusinessManagerDetailActivity) getActivity()).id);
        presenter.getListReport(URLs.LISTREPORT, map);
    }
    /*private void generatedata() {
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
    }*/

    @Override
    protected void updateView() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //CommonUtil.gotoActivity(getActivity(), BusinessManagerDetailActivity.class,false);
    }

    @Override
    public void onHttpResultSuccess() {
        List<FinancialReportsBean> lists_temp = new ArrayList<>();
        lists_temp.addAll(lists);
        lists.clear();
        int month_tag=0;
        for (FinancialReportsBean financialReportsBean : lists_temp) {
            String date=financialReportsBean.getDate();
            int month= AbDateUtil.getMonth(date, "yyyy-MM-dd");
            if(month_tag==0){
                month_tag=month;
                lists.add(new FinancialReportsBean(FinancialReportsBean.SECTION,month+"月"));
            }
            if(month_tag!=month){
                month_tag=month;
                lists.add(new FinancialReportsBean(FinancialReportsBean.SECTION,month+"月"));
            }
            financialReportsBean.setType(FinancialReportsBean.ITEM);
            financialReportsBean.setDate(AbDateUtil.getDay(date, "yyyy-MM-dd")+"号");
            lists.add(financialReportsBean);
        }
        financialReportsFragmentAdapter.notifyDataSetChanged();
    }
}
