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
public class PublishFragment04 extends BaseFragment {
    private TextView tv_dividendType,tv_proportion1,tv_proportion2;
    private EditText et_stockholderPrivilege;
    private String[] dividendtypes,proportion1,proportion2;
    private PublishProjectActivity publishProjectActivity;
    private String dividendtype,stockholderPrivilege;

    public PublishFragment04() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        publishProjectActivity = (PublishProjectActivity) getActivity();
        return inflater.inflate(R.layout.fragment_publish_fragment04, container, false);
    }


    @Override
    protected void initView(View view) {
        et_stockholderPrivilege=findViewByID_My(R.id.et_stockholderPrivilege);
        tv_dividendType=findViewByID_My(R.id.tv_dividendType);
        tv_proportion1 = findViewByID_My(R.id.tv_proportion1);
        tv_proportion2 = findViewByID_My(R.id.tv_proportion2);
        tv_dividendType.setOnClickListener(this);
        tv_proportion1.setOnClickListener(this);
        tv_proportion2.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        dividendtypes=getActivity().getResources().getStringArray(R.array.publishproject_dividendtypes);
        proportion1 = getActivity().getResources().getStringArray(R.array.publishproject_proportion1);
        proportion2 = getActivity().getResources().getStringArray(R.array.publishproject_proportion2);
    }

    @Override
    protected void updateView() {

    }

    public boolean putData(){
        if(match()){
            publishProjectActivity.params.put("dividendType", dividendtype);
            //publishProjectActivity.params.put("totalStockMoney", totalStockMoney);
            publishProjectActivity.params.put("stockholderPrivilege", stockholderPrivilege);
            //publishProjectActivity.params.put("perSellStockMoney", perSellStockMoney);
            return true;
        }
        return false;
    }

    private boolean match() {
        dividendtype = tv_dividendType.getText().toString().trim();
        if (dividendtype.equals(getString(R.string.choose_dividendtype))) {
            myToast("请输入分红模式");
            return false;
        }
        stockholderPrivilege = et_stockholderPrivilege.getText().toString().trim();
        if (TextUtils.isEmpty(stockholderPrivilege)) {
            myToast("请输入股东特权说明");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_dividendType:
                DialogTool.createRadioDialog(getActivity(), R.mipmap.ic_launcher, "分红模式", dividendtypes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_dividendType.setText(dividendtypes[which]);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.tv_proportion1:
                DialogTool.createRadioDialog(getActivity(), R.mipmap.ic_launcher, "分红比例", proportion1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_proportion1.setText(proportion1[which]);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.tv_proportion2:
                DialogTool.createRadioDialog(getActivity(), R.mipmap.ic_launcher, "分红比例", proportion2, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_proportion2.setText(proportion2[which]);
                        dialog.dismiss();
                    }
                });
                break;

        }
    }
}
