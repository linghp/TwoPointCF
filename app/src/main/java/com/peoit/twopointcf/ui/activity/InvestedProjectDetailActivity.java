package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.TagViewPager;

import java.util.ArrayList;
import java.util.List;

public class InvestedProjectDetailActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_subtitle;
    private TagViewPager tagViewPager;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project_detail);
    }

    public static void startThisActivity(String title, Context context) {
        Intent intent = new Intent(context, InvestedProjectDetailActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected void initView() {
        tv_subtitle= (TextView) findViewById(R.id.tv_subtitle);
        tagViewPager = (TagViewPager) findViewById(R.id.tagViewPager);
        //轮播
        List<Integer> imgLists = new ArrayList<>();
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        tagViewPager.toUse(imgLists, this);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.title_activity_invested_project_detail));
        if (TextUtils.isEmpty(title)) {

        } else {
            tv_subtitle.setText(title);
        }
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

        }
    }
}
