package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 个人简介
 * Created by zyz on 2015/9/2.
 */
public class PersonalProfileActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_personalprofile;
    private TextView tv_personalprofile;
    private String edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalprofile);
    }

    @Override
    protected void initView() {
        et_personalprofile = (EditText) findViewById(R.id.personalprofile_et);
        tv_personalprofile = (TextView) findViewById(R.id.personalprofile_tv);
    }

    @Override
    protected void initData() {

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
                edit = et_personalprofile.getText().toString();
                myToast("输入框内容:"+edit);
                break;
            default:
                break;
        }

    }
}
