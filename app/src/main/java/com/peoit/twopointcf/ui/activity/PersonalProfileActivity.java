package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.PersonalProfilePresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.LocalUserInfo;

/**
 * 个人简介
 * Created by zyz on 2015/9/2.
 */
public class PersonalProfileActivity extends BaseActivity implements PersonalProfilePresenter.OnHttpResultListener {
    private EditText et_personalprofile;
    private TextView tv_personalprofile;
    private String edit;
    private PersonalProfilePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalprofile);
    }

    @Override
    protected void initView() {
        et_personalprofile = (EditText) findViewById(R.id.personalprofile_et);
        tv_personalprofile = (TextView) findViewById(R.id.personalprofile_tv);
        String personalprofile=localUserInfo.getuserCaption();
        if(personalprofile.equals("无")){
            personalprofile="";
        }
        et_personalprofile.setText(personalprofile);
    }

    @Override
    protected void initData() {
        presenter = new PersonalProfilePresenter(this);
    }

    @Override
    protected void updateView() {

        if(titleView!=null) {
            titleView.setTitle("个人简介");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personalprofile_tv:
                edit = et_personalprofile.getText().toString().trim();
                if (TextUtils.isEmpty(edit)){
                    myToast("请输入个人简介");
                }else {
                    presenter.getUserCaption(edit);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onHttpResultSuccess() {

    }

    @Override
    public LocalUserInfo getLocalUserInfo() {
        return localUserInfo;
    }
}
