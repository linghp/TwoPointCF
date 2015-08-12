package com.peoit.twopointcf.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.BusinessDynamicsBean;
import com.peoit.twopointcf.ui.adapter.BusinessDynamicsFragmentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ling
 *         经营动态
 */
public class BusinessDynamicsFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private View mParent;

    private Activity mActivity;
    private ListView listView;
    private BusinessDynamicsFragmentAdapter adapter;
    private List<BusinessDynamicsBean> businessDynamicsBeans=new ArrayList<>();

    public static BusinessDynamicsFragment newInstance(int index) {
        BusinessDynamicsFragment f = new BusinessDynamicsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_businessdynamics, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent = getView();
        mActivity = getActivity();
        if (titleView != null) {
            titleView.hideLeftBtn();
            titleView.setTitle(R.string.businessdynamics);
        }

    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.mylistview);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        generatedata();
        adapter=new BusinessDynamicsFragmentAdapter(getActivity(),businessDynamicsBeans);
        listView.setAdapter(adapter);
    }

    private void generatedata() {
        businessDynamicsBeans.add(new BusinessDynamicsBean(BusinessDynamicsBean.SECTION,"2015年8月","",""));
        businessDynamicsBeans.add(new BusinessDynamicsBean(BusinessDynamicsBean.ITEM,"8.10","金佳俊宠物度假村","跟对方答复说分手费大幅度发鬼地方地方地方发放到地方地方的法规的地方改地方的法规"));
        businessDynamicsBeans.add(new BusinessDynamicsBean(BusinessDynamicsBean.ITEM,"8.2","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
        businessDynamicsBeans.add(new BusinessDynamicsBean(BusinessDynamicsBean.SECTION,"2015年7月","",""));
        businessDynamicsBeans.add(new BusinessDynamicsBean(BusinessDynamicsBean.ITEM,"7.28","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
        businessDynamicsBeans.add(new BusinessDynamicsBean(BusinessDynamicsBean.ITEM,"7.15","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
        businessDynamicsBeans.add(new BusinessDynamicsBean(BusinessDynamicsBean.ITEM,"7.10","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
    }


    @Override
    protected void updateView() {

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
