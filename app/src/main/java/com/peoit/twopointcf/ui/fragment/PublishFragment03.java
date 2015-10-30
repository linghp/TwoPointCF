package com.peoit.twopointcf.ui.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.activity.PublishProjectActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.DataPickDialogUtil;
import com.peoit.twopointcf.utils.DialogTool;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishFragment03 extends BaseFragment {
    private EditText et_totalStockMoney, et_sellStockMoney, et_perSellStockMoney;
    private TextView et_moneyUse,tv_stocktype, tv_endDate, tv_successCondition, tv_proportion;
    private String moneyUse, totalStockMoney, sellStockMoney, perSellStockMoney, stocktype, endDate, successCondition;
    private PublishProjectActivity publishProjectActivity;
    private String[] stockTypes, proportion,moneyUses;
    private int totalStockMoney_int,sellStockMoney_int,perSellStockMoney_int;

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
        et_moneyUse = findViewByID_My(R.id.et_moneyUse);
        et_totalStockMoney = findViewByID_My(R.id.et_totalStockMoney);
        et_sellStockMoney = findViewByID_My(R.id.et_sellStockMoney);
        et_perSellStockMoney = findViewByID_My(R.id.et_perSellStockMoney);
        tv_stocktype = findViewByID_My(R.id.tv_stocktype);
        tv_proportion = findViewByID_My(R.id.tv_proportion);
        tv_endDate = findViewByID_My(R.id.tv_endDate);
        tv_successCondition = findViewByID_My(R.id.tv_successCondition);

        et_moneyUse.setOnClickListener(this);
        tv_stocktype.setOnClickListener(this);
        tv_endDate.setOnClickListener(this);
        tv_successCondition.setOnClickListener(this);


        et_totalStockMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (et_totalStockMoney.hasFocus() == false) {
                    totalStockMoney = et_totalStockMoney.getText().toString().trim();
                    matchTotalStockMoney();

                }
            }
        });

        et_perSellStockMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (et_perSellStockMoney.hasFocus() == false) {
                    perSellStockMoney = et_perSellStockMoney.getText().toString().trim();
                    matchPerSellStockMoney();

                }
            }
        });

        et_sellStockMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
//                String totalStockMoney=et_totalStockMoney.getText().toString().trim();
//                if(!TextUtils.isEmpty(totalStockMoney)) {
//                    int totalStockMoney_int=Integer.parseInt(totalStockMoney);
                if(totalStockMoney_int>0){
                    if (!TextUtils.isEmpty(s1)) {
                        sellStockMoney_int = Integer.parseInt(s1);
                        if(sellStockMoney_int>totalStockMoney_int){
                            myToast("发行总额不能大于股权总额");
                        }else{
                            String percentage = CommonUtil.twoPointConversion(sellStockMoney_int / (totalStockMoney_int + 0.0) * 100) + "%";
                            tv_proportion.setText(percentage);
                        }
                    } else {
                        tv_proportion.setText("0.00%");
                    }
                }
            }
        });

    }

    private boolean matchPerSellStockMoney() {
        if (TextUtils.isEmpty(perSellStockMoney)) {
            myToast("请输入售卖金额");
            return true;
        } else {
            perSellStockMoney_int = Integer.parseInt(perSellStockMoney);
            if (sellStockMoney_int > 0) {
                if (perSellStockMoney_int>sellStockMoney_int){
                    myToast("售卖金额不能大于发行总额");
                    return true;
                }else if(perSellStockMoney_int%1000!=0) {
                    myToast("售卖金额必须是1000的倍数");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean matchTotalStockMoney() {
        if (TextUtils.isEmpty(totalStockMoney)) {
            myToast("请输入股权总额");
            return true;
        }else {
            totalStockMoney_int = Integer.parseInt(totalStockMoney);
            if (sellStockMoney_int > 0) {
                if (totalStockMoney_int < sellStockMoney_int) {
                    myToast("股权总额应大于售卖总额");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void initData() {
        //传过来的数据
        if (publishProjectActivity.projectBean != null){
            et_moneyUse.setText(publishProjectActivity.projectBean.moneyUse);//融资用途
            et_totalStockMoney.setText(publishProjectActivity.projectBean.totalStockMoney+"");//股权总额
            et_sellStockMoney.setText(publishProjectActivity.projectBean.sellStockMoney+"");//发行总额
            tv_proportion.setText((publishProjectActivity.projectBean.sellStockMoney + 0.0) / publishProjectActivity.projectBean.totalStockMoney * 100 + "%");//起售股份所占比例
            et_perSellStockMoney.setText(publishProjectActivity.projectBean.perSellStockMoney+"");//售卖金额
            tv_stocktype.setText(publishProjectActivity.projectBean.stockType);//股权类型
            tv_endDate.setText(publishProjectActivity.projectBean.endDate);//众筹结束时间
            tv_successCondition.setText(Math.round(publishProjectActivity.projectBean.successCondition * 100) +"%");//项目启动条件
        }

        moneyUses = getActivity().getResources().getStringArray(R.array.publishproject_moneyUse);
        stockTypes = getActivity().getResources().getStringArray(R.array.publishproject_stocktypes);
        proportion = getActivity().getResources().getStringArray(R.array.publishproject_proportion);
    }

    @Override
    protected void updateView() {

    }

    public boolean putData() {
        if (match()) {
            float i = Float.valueOf(successCondition.replace("%",""));
            publishProjectActivity.params.put("moneyUse", moneyUse);
            publishProjectActivity.params.put("totalStockMoney", totalStockMoney);
            publishProjectActivity.params.put("sellStockMoney", sellStockMoney);
            publishProjectActivity.params.put("perSellStockMoney", perSellStockMoney);
//            publishProjectActivity.params.put("proportion", perSellStockMoney);
            publishProjectActivity.params.put("stockType", stocktype);
            publishProjectActivity.params.put("endDate", endDate);
            publishProjectActivity.params.put("successCondition", i/100+"");
            return true;
        }
        return false;
    }

    private boolean match() {
        moneyUse = et_moneyUse.getText().toString().trim();
        /*if (TextUtils.isEmpty(moneyUse)) {
            myToast("请输入融资用途");
            return false;
        }*/
        if (moneyUse.equals(getString(R.string.choosemoneyUse))) {
            myToast("请选择融资用途");
            return false;
        }
        totalStockMoney = et_totalStockMoney.getText().toString().trim();
        if (matchTotalStockMoney()) {
            return false;
        }
        sellStockMoney = et_sellStockMoney.getText().toString().trim();
        if (TextUtils.isEmpty(sellStockMoney)) {
            myToast("请输入发行总额");
            return false;
        }

        if(sellStockMoney_int>0&&totalStockMoney_int>0&&sellStockMoney_int>totalStockMoney_int){
            myToast("发行总额不能大于股权总额");
            return false;
        }

        perSellStockMoney = et_perSellStockMoney.getText().toString().trim();
        if (matchPerSellStockMoney()) {
            return false;
        }
        int perSellStockMoney_int=Integer.parseInt(perSellStockMoney);
        if (perSellStockMoney_int%1000!=0) {
            myToast("售卖金额必须是1000的倍数");
            return false;
        }
        stocktype = tv_stocktype.getText().toString().trim();
        if (stocktype.equals(getString(R.string.choosestocktype))) {
            myToast("请选择股权类型");
            return false;
        }
        endDate = tv_endDate.getText().toString().trim();
        if (TextUtils.isEmpty(endDate)){
            myToast("请选择众筹结束时间");
            return false;
        }
        if (endDate.equals(getString(R.string.choosesendDate))) {
            myToast("请选择众筹结束时间");
            return false;
        }
        successCondition = tv_successCondition.getText().toString().trim();
        if (successCondition.equals(getString(R.string.choosessuccessCondition))) {
            myToast("请选择项目启动条件");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            /*case R.id.tv_proportion:
                //起售股份所占比例
                break;*/
            case R.id.tv_stocktype:
                //股权类型
                DialogTool.createRadioDialog(getActivity(), R.mipmap.ic_launcher, "股权类型", stockTypes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_stocktype.setText(stockTypes[which]);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.tv_endDate:
                //众筹结束时间（日期）
                DataPickDialogUtil dataPickDialogUtil = new DataPickDialogUtil(getActivity());
                dataPickDialogUtil.dateTimePicKDialog(tv_endDate);
                break;
            case R.id.tv_successCondition:
                //项目启动条件
                DialogTool.createRadioDialog(getActivity(), R.mipmap.ic_launcher, "项目启动条件", proportion, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_successCondition.setText(proportion[which]);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.et_moneyUse:
                //项目启动条件
                DialogTool.createRadioDialog(getActivity(), R.mipmap.ic_launcher, "项目融资用途", moneyUses, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_moneyUse.setText(moneyUses[which]);
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }
}
