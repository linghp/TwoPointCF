package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.ui.activity.InvestFindDetailActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;

/**
 * Created by ling on 2015/8/7.
 * last:2015/8/7
 * description:
 */
public class InvestFindDetailSub3Fragment extends BaseFragment {
    private ProjectBean projectBean;
    private TextView tv_01;

    public InvestFindDetailSub3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest_find_detail_sub3, container, false);
    }


    @Override
    protected void initView(View view) {
        tv_01=findViewByID_My(R.id.tv_01);
    }

    @Override
    protected void initData() {
        projectBean=((InvestFindDetailActivity)getActivity()).projectBean;
    }

    @Override
    protected void updateView() {
        if(projectBean!=null){
            tv_01.setText(projectBean.sourceOfIncome);
        }
    }
}
