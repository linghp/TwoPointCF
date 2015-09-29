package com.peoit.twopointcf.ui.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.activity.PublishProjectActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.DialogTool;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishFragment03 extends BaseFragment {
    private EditText et_moneyUse, et_totalStockMoney, et_sellStockMoney, et_perSellStockMoney;
    private TextView tv_stocktype, tv_endDate, tv_successCondition;
    private String moneyUse,totalStockMoney,sellStockMoney,perSellStockMoney,stocktype,endDate,successCondition;
    private PublishProjectActivity publishProjectActivity;
    private String[] stockTypes;
    public PublishFragment03() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        publishProjectActivity = (PublishProjectActivity) getActivity();
        return inflater.inflate(R.layout.fragment_publish_fragment03, container, false);
    }

    @Override
    protected void initView(View view) {
        et_moneyUse=findViewByID_My(R.id.et_moneyUse);
        et_totalStockMoney=findViewByID_My(R.id.et_totalStockMoney);
        et_sellStockMoney=findViewByID_My(R.id.et_sellStockMoney);
        et_perSellStockMoney=findViewByID_My(R.id.et_perSellStockMoney);
        tv_stocktype=findViewByID_My(R.id.tv_stocktype);
        tv_endDate=findViewByID_My(R.id.tv_endDate);
        tv_successCondition=findViewByID_My(R.id.tv_successCondition);

        tv_stocktype.setOnClickListener(this);
        tv_endDate.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        stockTypes=getActivity().getResources().getStringArray(R.array.publishproject_stocktypes);
    }

    @Override
    protected void updateView() {

    }

    public boolean putData(){
        if(match()){
            publishProjectActivity.params.put("moneyUse", moneyUse);
            publishProjectActivity.params.put("totalStockMoney", totalStockMoney);
            publishProjectActivity.params.put("sellStockMoney", sellStockMoney);
            publishProjectActivity.params.put("perSellStockMoney", perSellStockMoney);
//            publishProjectActivity.params.put("endDate", industryType);
//            publishProjectActivity.params.put("successCondition", industryType);
            return true;
        }
        return false;
    }

    private boolean match() {
        moneyUse = et_moneyUse.getText().toString().trim();
        if (TextUtils.isEmpty(moneyUse)) {
            myToast("请输入融资用途");
            return false;
        }
        totalStockMoney = et_totalStockMoney.getText().toString().trim();
        if (TextUtils.isEmpty(totalStockMoney)) {
            myToast("请输入股权总额");
            return false;
        }
        sellStockMoney = et_sellStockMoney.getText().toString().trim();
        if (TextUtils.isEmpty(sellStockMoney)) {
            myToast("请输入发行总额");
            return false;
        }
        perSellStockMoney = et_perSellStockMoney.getText().toString().trim();
        if (TextUtils.isEmpty(perSellStockMoney)) {
            myToast("请输入售卖金额");
            return false;
        }
//        stocktype = tv_stocktype.getText().toString().trim();
//        if (TextUtils.isEmpty(stocktype)) {
//            myToast("请输入股权总额");
//            return false;
//        }
//        endDate = tv_endDate.getText().toString().trim();
//        if (TextUtils.isEmpty(endDate)) {
//            myToast("众筹结束时间");
//            return false;
//        }

        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_stocktype:
                DialogTool.createRadioDialog(getActivity(), R.mipmap.ic_launcher, "股权类型", stockTypes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_stocktype.setText(stockTypes[which]);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.tv_endDate:

                break;
        }
    }
}
