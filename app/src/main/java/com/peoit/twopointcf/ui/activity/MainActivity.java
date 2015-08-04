package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.view.FragmentIndicator;


public class MainActivity extends AppCompatActivity {
    public static Fragment[] mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragmentIndicator(0);
        Toast.makeText(this,"test",0).show();
    }

    /**
     * 初始化fragment
     */
    private void setFragmentIndicator(int whichIsDefault) {
        mFragments = new Fragment[4];
        mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.fragment_MyCenter);
        mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.fragment_InvestFind);
        mFragments[2] = getSupportFragmentManager().findFragmentById(R.id.fragment_BusinessDynamics);
        mFragments[3] = getSupportFragmentManager().findFragmentById(R.id.fragment_MyProject);
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
