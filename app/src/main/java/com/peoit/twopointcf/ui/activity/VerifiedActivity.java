package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.modules.chooseimages.ChooseImages;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 实名认证
 * Created by zyz on 2015/8/28.
 */
public class VerifiedActivity extends BaseActivity implements View.OnClickListener{
    private EditText et_name;
    private EditText et_num;
    private EditText et_money;
    private EditText et_earnings;//收益
    private EditText et_name1;
    private EditText et_phone;
    private ImageView iv_photo1;
    private ImageView iv_photo2;
    private TextView tv_define;//确定
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
    }

    @Override
    protected void initView() {
        et_name = (EditText) findViewById(R.id.verified_et1);
        et_num = (EditText) findViewById(R.id.verified_et2);
        et_money = (EditText) findViewById(R.id.verified_et3);
        et_earnings = (EditText) findViewById(R.id.verified_et4);
        et_name1 = (EditText) findViewById(R.id.verified_et5);
        et_phone = (EditText) findViewById(R.id.verified_et6);
        iv_photo1 = (ImageView) findViewById(R.id.verified_iv1);
        iv_photo2 = (ImageView) findViewById(R.id.verified_iv2);
        tv_define = (TextView) findViewById(R.id.verified_tv1);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("实名认证");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.verified_tv1:
                myToast("确定");
                break;
            case R.id.verified_iv1:
                ChooseImages.showPhotoDialog(this);
                break;
            case R.id.verified_iv2:
                ChooseImages.showPhotoDialog(this);
                break;
            default:
                break;
        }
    }
}
