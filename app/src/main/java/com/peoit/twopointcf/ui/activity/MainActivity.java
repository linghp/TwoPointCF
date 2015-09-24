package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.view.FragmentIndicator;


public class MainActivity extends AppCompatActivity {
    public static Fragment[] mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragmentIndicator(0);
    }

    /**
     * 初始化fragment
     */
    private void setFragmentIndicator(int whichIsDefault) {
        mFragments = new Fragment[4];
        mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.fragment_InvestFind);//投资发现
        mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.fragment_BusinessDynamics);//资讯中心
        mFragments[2] = getSupportFragmentManager().findFragmentById(R.id.fragment_MyProject);//我的项目
        mFragments[3] = getSupportFragmentManager().findFragmentById(R.id.fragment_MyCenter);//个人中心
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).show(mFragments[whichIsDefault]).commit();

        FragmentIndicator mIndicator = (FragmentIndicator) findViewById(R.id.indicator);
        //FragmentIndicator.setIndicator(whichIsDefault);
        mIndicator.setOnIndicateListener(new FragmentIndicator.OnIndicateListener() {
            @Override
            public void onIndicate(View v, int which) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]).hide(mFragments[3]).show(mFragments[which]).commit();
            }
        });
    }

}
