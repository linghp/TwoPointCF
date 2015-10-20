package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.ui.activity.InvestActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment02 extends BaseFragment {
    @Bind(R.id.tv_projectname)
    TextView tvProjectname;
    @Bind(R.id.tv_stockcount)
    TextView tvStockcount;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_investorEarnest)
    TextView tvInvestorEarnest;
    @Bind(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;
    @Bind(R.id.ll_bindPhoneNumber)
    LinearLayout llBindPhoneNumber;

    private ProjectBean projectBean;

    public InvestFragment02() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invest_fragment02, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void initView(View view) {
        llBindPhoneNumber.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        projectBean = ((InvestActivity) mActivity).projectBean;
    }

    @Override
    protected void updateView() {
        if(projectBean!=null) {
            tvProjectname.setText(projectBean.projectName);
            tvStockcount.setText(projectBean.mStockPercent);
            tvMoney.setText(CommonUtil.twoPointConversion(projectBean.mInvestMoney / 10000.0) + "万");
            tvInvestorEarnest.setText(projectBean.mInvestorEarnest + "元");
            String phoneNumber = localUserInfo.getPhonenumber();
            String temp = phoneNumber.substring(3, 7);
            tvPhoneNumber.setText(phoneNumber.replaceFirst(temp, "****"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_bindPhoneNumber:
                myToast("bind");
                break;
        }
    }
}
