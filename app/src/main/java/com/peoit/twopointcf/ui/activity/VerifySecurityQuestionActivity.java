package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * 验证密保问题
 * Created by zyz on 2015/9/14.
 */
public class VerifySecurityQuestionActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_question;//密保问题
    private EditText et_answer;//答案
    private TextView tv_define;//确认
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifysecurityquestion);
    }

    @Override
    protected void initView() {
        tv_question = (TextView) findViewById(R.id.verifysecurityquestion_tv1);

        et_answer = (EditText) findViewById(R.id.verifysecurityquestion_et1);
        tv_define = (TextView) findViewById(R.id.verifysecurityquestion_tv2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("验证密保问题");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.verifysecurityquestion_tv2:
                myToast("验证密保通过");
                CommonUtil.gotoActivity(this,ChangePasswordActivity.class,true);
                break;
            default:
                break;
        }


    }
}
