package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.presenters.impl.ChangePasswordPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.LocalUserInfo;

/**
 * 修改手机号/
 * ischange true/false
 * Created by zyz on 2015/8/29.
 */
public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener, ChangePasswordPresenter.OnHttpResultListener {
    private TextView boundphoneTv1;
    private EditText et_phonenum;
    private String phonenum;
    private ChangePasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephone);
    }

    @Override
    protected void initView() {
        et_phonenum = (EditText) findViewById(R.id.boundphone_et1);
        boundphoneTv1 = (TextView) findViewById(R.id.boundphone_tv1);

    }

    @Override
    protected void initData() {
        presenter = new ChangePasswordPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("修改手机号码");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boundphone_tv1:
                    if (match()) {
                        presenter.getChangePhone(phonenum);
                    }
                break;
            default:
                break;
        }

    }

    private boolean match() {
        phonenum = et_phonenum.getText().toString();
        if (TextUtils.isEmpty(phonenum)) {
            showToast("请输入手机号码");
            return false;
        } else {
            if (isMobileNO(phonenum)) {

            } else {
                showToast("手机号不合法");
                return false;
            }
        }
        return true;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return mobiles.matches(telRegex);
    }

    @Override
    public void onHttpResultSuccess() {

    }

    @Override
    public LocalUserInfo getLocalUserInfo() {
        return localUserInfo;
    }
}
