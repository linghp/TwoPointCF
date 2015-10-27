package com.peoit.twopointcf.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.ui.activity.InvestActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.HashMap;

/**
 * 投资
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment01 extends BaseFragment{
    private TextView tv_projectname, tv_initiator, tv_money, tv_percentage;
    private EditText et_stockcount, tv_description;
    private ProjectBean projectBean;
    //private IInvestProject presenter;
    private int stockcount;
    public double investorEarnest;
    double money;

    public InvestFragment01() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invest_fragment01, container, false);
    }


    @Override
    protected void initView(View view) {
        tv_projectname = findViewByID_My(R.id.tv_projectname);
        tv_initiator = findViewByID_My(R.id.tv_initiator);
        tv_money = findViewByID_My(R.id.tv_money);
        tv_percentage = findViewByID_My(R.id.tv_percentage);
        et_stockcount = findViewByID_My(R.id.et_stockcount);
        tv_description = findViewByID_My(R.id.tv_description);

        et_stockcount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (!TextUtils.isEmpty(s1)) {
                    stockcount = Integer.parseInt(s1);
                    money = stockcount * (projectBean.perSellStockMoney+0.0);
                    investorEarnest=money*projectBean.investorEarnestPercent;
                    String money_tt = CommonUtil.twoPointConversion(money / 10000.0) + "万";
                    String percentage = CommonUtil.twoPointConversion(money / (projectBean.totalStockMoney + 0.0)*100) + "%";
                    tv_money.setText(money_tt);
                    tv_percentage.setText(percentage);

                    projectBean.mStockPercent=percentage;
                    projectBean.mInvestMoney=(int)money;
                    projectBean.mInvestorEarnest=(int)investorEarnest;
                }else{
                    stockcount=0;
                }
            }
        });
    }

    @Override
    protected void initData() {
        projectBean = ((InvestActivity) mActivity).projectBean;
        //presenter=new InvestProjectPresenter(this);
    }

    @Override
    protected void updateView() {
        if (projectBean != null) {
            tv_projectname.setText(projectBean.projectName);
            tv_initiator.setText(projectBean.publisherName);
        }
    }

    @Override
    public void requestServer() {//这个用来判断参数状态
        super.requestServer();
        HashMap<String, String> params = mActivity.params;
        if(match()) {
            params.put("projectId",projectBean.id);
            params.put("userId",localUserInfo.getUserId());
            params.put("amount",money+"");
            params.put("description",tv_description.getText().toString().trim());
            mActivity.onResultSuccess();
        }
    }

    private boolean match() {
       // projectName = et_projectName.getText().toString().trim();
        if (stockcount<=0) {
            myToast("请输入投资股数");
            return false;
        }
        if (stockcount>(projectBean.sellStockMoney/projectBean.perSellStockMoney)) {
            myToast("您输入的投资股数已超出总股数");
            return false;
        }
        /*if (money > (projectBean.perSellStockMoney - projectBean.sellStockMoney) ){
            myToast("您输入的投资股数已超出总股数");
            return false;
        }*/

        return true;
    }

//    @Override
//    public void onHttpResultSuccess() {
//        mActivity.onHttpResultSuccess();
//    }
}
