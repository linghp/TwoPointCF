package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub1Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub2Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub3Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub4Fragment;
import com.peoit.twopointcf.ui.view.TagViewPager;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class InvestFindDetailActivity extends BaseActivity implements View.OnClickListener {
    private TagViewPager tagViewPager;
    private TextView tv_subtitle;
    private LinearLayout linearLayoutsub;
    private BaseFragment firstFragment, secondFragment, thirdFragment, fourthFragment;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_find_detail);
    }

    public static void startThisActivity(String title, Context context) {
        Intent intent = new Intent(context, InvestFindDetailActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        tagViewPager = (TagViewPager) findViewById(R.id.tagViewPager);
        tv_subtitle= (TextView) findViewById(R.id.tv_subtitle);
        linearLayoutsub=findViewByID_My(R.id.linearLayoutsub);
        findViewById(R.id.slide_tag1).setActivated(true);

        ViewGroup.LayoutParams layoutParams=tagViewPager.getLayoutParams();
        layoutParams.width= CommonUtil.getScreenWidth(this);
        layoutParams.height = layoutParams.width / 3;
        tagViewPager.setLayoutParams(layoutParams);
        //轮播
        List<Integer> imgLists = new ArrayList<>();
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        tagViewPager.toUse(imgLists, this);
    }

    @Override
    protected void initData() {
        firstFragment = new InvestFindDetailSub1Fragment();
        secondFragment = new InvestFindDetailSub2Fragment();
        thirdFragment = new InvestFindDetailSub3Fragment();
        fourthFragment = new InvestFindDetailSub4Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, firstFragment, "firstFragment").commit();

        title = getIntent().getStringExtra("title");
        String[] investfinddetail_subitemvalues={"800万元","32人","50.00%","60000元","0.75%",
                "重庆","红利股","2015-09-21","未知","3.75%",
                "无","5.00%","众筹完成","食品","重庆市渝中区大坪",
                "点击查看","点击查看","点击查看"};
        String[] investfinddetail_subitemnames=getResources().getStringArray(R.array.investfinddetail_subitemname);
        for (int i = 0; i < investfinddetail_subitemnames.length; i++) {
            View view=inflater.inflate(R.layout.activity_invest_find_detail_subitem,null);
            TextView tv01= (TextView) view.findViewById(R.id.tv_01);
            TextView tv02= (TextView) view.findViewById(R.id.tv_02);
            tv01.setText(investfinddetail_subitemnames[i]);
            tv02.setText(investfinddetail_subitemvalues[i]);
            linearLayoutsub.addView(view);
            final String title=investfinddetail_subitemnames[i];
            if(investfinddetail_subitemvalues[i].contains("点击查看")){
                tv02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimplePhotoViewActivity.startThisActivity(title,InvestFindDetailActivity.this);
                    }
                });
            }
        }
    }

    @Override
    protected void updateView() {
        if (TextUtils.isEmpty(title)) {
            titleView.setTitle("金佳俊宠物度假村");
        } else {
            titleView.setTitle(title);
            tv_subtitle.setText(title);
        }
        titleView.getBtn_right().setTag(R.mipmap.collection);
        titleView.setRightBtn(R.mipmap.collection, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getTag().equals(R.mipmap.collection)) {
                    v.setTag(R.mipmap.collection_on);
                    titleView.setRightBtn(R.mipmap.collection_on, this);
                }else {
                    v.setTag(R.mipmap.collection);
                    titleView.setRightBtn(R.mipmap.collection, this);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int tag = (int) v.getTag();
            switch (tag) {//轮播图的每一张的tag
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, secondFragment, "secondFragment").commit();
                    break;
                case R.id.slide_tag3:
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(true);
                    findViewById(R.id.slide_tag4).setActivated(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, thirdFragment, "thirdFragment").commit();
                    break;
                case R.id.slide_tag4:
                    findViewById(R.id.slide_tag1).setActivated(false);
                    findViewById(R.id.slide_tag2).setActivated(false);
                    findViewById(R.id.slide_tag3).setActivated(false);
                    findViewById(R.id.slide_tag4).setActivated(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, fourthFragment, "fourthFragment").commit();
                    //v.setActivated(true);
                    //myToast("test");
                    break;
                case R.id.tv_toinvest:
                    CommonUtil.gotoActivity(this,InvestActivity.class,false);
                    break;
            }
        }
    }
}
