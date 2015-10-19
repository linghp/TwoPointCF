package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.UserLevelBean;
import com.peoit.twopointcf.presenters.impl.ChangePasswordPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.CustomShapeImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的等级
 * Created by zyz on 2015/8/31.
 */
public class MyRatingActivity extends BaseActivity implements View.OnClickListener , ChangePasswordPresenter.OnHttpResultListener{
    private CustomShapeImageView ivMylevel1;
    private TextView tvMylevel1;
    private TextView tvMylevel2;
    private TextView tvMylevel3;
    private TextView tvMylevel4;

    private ChangePasswordPresenter presenter;
    private UserLevelBean userLevelBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrating);
    }



    @Override
    protected void initView() {
        ivMylevel1 = (CustomShapeImageView) findViewById(R.id.iv_mylevel1);
        tvMylevel1 = (TextView) findViewById(R.id.tv_mylevel1);
        tvMylevel2 = (TextView) findViewById(R.id.tv_mylevel2);
        tvMylevel3 = (TextView) findViewById(R.id.tv_mylevel3);
        tvMylevel4 = (TextView) findViewById(R.id.tv_mylevel4);

    }

    @Override
    protected void initData() {
        presenter = new ChangePasswordPresenter(this);
        //获取用户等级
        Map<String, String> maps = new HashMap<>();
        maps.put("userId", localUserInfo.getUserId());
        presenter.getUserLevel(maps, new ChangePasswordPresenter.OnUserLevelCallBack() {
            @Override
            public void onSueccess(UserLevelBean bean) {
                tvMylevel3.setText(bean.getInvested());//投资项目统计数
                tvMylevel4.setText(bean.getPublished());// 发布项目统计数
            }
        });

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("我的等级");
        }
        tvMylevel1.setText(localUserInfo.getUsername());
//        tvMylevel2.setText();//积分

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onHttpResultSuccess() {

    }
}
