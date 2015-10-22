package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.presenters.impl.InvestProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.IInvestProject;
import com.peoit.twopointcf.ui.activity.InvestActivity;
import com.peoit.twopointcf.ui.activity.MainActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.MyLogger;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 支付保证金
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment03 extends BaseFragment implements InvestProjectPresenter.OnHttpResultListener {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_orderMoney)
    TextView tvOrderMoney;
    @Bind(R.id.tv_yetMoney)
    TextView tvYetMoney;
    private IInvestProject presenter;
    private ProjectBean projectBean;

    public InvestFragment03() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invest_fragment03, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        projectBean = ((InvestActivity) mActivity).projectBean;
        presenter = new InvestProjectPresenter(this);
    }

    @Override
    protected void updateView() {
        if(projectBean!=null) {
            tvName.setText(projectBean.projectName+"股权购买定金");
            tvOrderMoney.setText(projectBean.mInvestMoney+"元");
            tvYetMoney.setText(projectBean.mInvestMoney+"元");
        }
    }

    public void requestServer(Map<String, String> maps) {
        /*maps.put("projectId", projectBean.id);
        maps.put("userId", localUserInfo.getUserId());
        maps.put("projectId", projectBean.mInvestorEarnest+"");
        maps.put("description", "呵呵呵");//投资说明*/
        MyLogger.i("投资传入的参数："+maps);
        presenter.toInvest(maps);
    }

    @Override
    public void onHttpResultSuccess() {
        //myToast("支付成功");

        //mActivity.finish();
        CommonUtil.gotoActivity(mActivity,MainActivity.class,true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
