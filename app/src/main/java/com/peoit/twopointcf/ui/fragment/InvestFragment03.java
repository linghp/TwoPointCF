package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.InvestProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.IInvestProject;
import com.peoit.twopointcf.ui.base.BaseFragment;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment03 extends BaseFragment implements InvestProjectPresenter.OnHttpResultListener {
    private IInvestProject presenter;

    public InvestFragment03() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest_fragment03, container, false);
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        presenter=new InvestProjectPresenter(this);
    }

    @Override
    protected void updateView() {

    }

    public void requestServer(Map<String,String> maps){
        presenter.toInvest(maps);
    }

    @Override
    public void onHttpResultSuccess() {
        myToast("支付成功");
        mActivity.finish();
    }
}
