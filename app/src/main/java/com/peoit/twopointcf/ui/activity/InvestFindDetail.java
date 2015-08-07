package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub1Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub2Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub3Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub4Fragment;
import com.peoit.twopointcf.ui.view.TagViewPager;
import com.peoit.twopointcf.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class InvestFindDetail extends BaseActivity implements View.OnClickListener{
    private TagViewPager tagViewPager;
    private BaseFragment firstFragment,secondFragment,thirdFragment,fourthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_find_detail);
    }

    @Override
    protected void initData() {
        firstFragment=new InvestFindDetailSub1Fragment();
        secondFragment=new InvestFindDetailSub2Fragment();
        thirdFragment=new InvestFindDetailSub3Fragment();
        fourthFragment=new InvestFindDetailSub4Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, firstFragment,"firstFragment").commit();
    }

    @Override
    protected void initView() {
        tagViewPager = (TagViewPager) findViewById(R.id.tagViewPager);

        titleView.setTitle("金佳俊宠物度假村");
        findViewById(R.id.slide_tag1).setActivated(true);
        //轮播
        List<Integer> imgLists=new ArrayList<>();
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        tagViewPager.toUse(imgLists,this);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int tag = (int) v.getTag();
            switch (tag) {
                case 0:
                    // myToast(tag);
                case 1:
                    // myToast(tag);
                case 2:
                    //myToast(tag);
                case 3:
                    myToast(tag + "");
                    break;
            }
        } else {
            MyLogger.i("xxx");
            switch (v.getId()) {

                case R.id.slide_tag1:
                    findViewById(R.id.slide_tag1).setActivated(true);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(false);
                    findViewById(R.id.slide_tag4).setActivated(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, firstFragment, "firstFragment").commit();
                    break;
                case R.id.slide_tag2:
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(true);
                    findViewById(R.id.slide_tag3).setActivated(false);
                    findViewById(R.id.slide_tag4).setActivated(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, secondFragment,"secondFragment").commit();
                    break;
                case R.id.slide_tag3:
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(true);
                    findViewById(R.id.slide_tag4).setActivated(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, thirdFragment,"thirdFragment").commit();
                    break;
                case R.id.slide_tag4:
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(false);
                    findViewById(R.id.slide_tag4).setActivated(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, fourthFragment,"fourthFragment").commit();
                    //v.setActivated(true);
                    //myToast("test");
                    break;
            }
        }
    }
}
