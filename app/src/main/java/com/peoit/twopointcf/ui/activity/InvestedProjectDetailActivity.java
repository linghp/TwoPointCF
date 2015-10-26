package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.view.TagViewPager;
import com.peoit.twopointcf.utils.MyLogger;
/**
 * @author ling
 * 已投项目详情 弃用 （由于和投资法相项目详情一样。。）
 */
public class InvestedProjectDetailActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_subtitle;
    private TagViewPager tagViewPager;
    private String title;
    private ProjectBean projectBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invested_project_detail);
        MyLogger.i("123");
        Log.i("InvestedProjectDetai","123");
    }

    public static void startThisActivity(ProjectBean projectBean, Context context) {
        Intent intent = new Intent(context, InvestedProjectDetailActivity.class);
        intent.putExtra("projectBean", projectBean);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        projectBean = (ProjectBean) getIntent().getSerializableExtra("projectBean");
        if(projectBean!=null){
            //轮播
            tagViewPager.toUse(projectBean.projectPhotos, this);
        }
    }

    @Override
    protected void initView() {
        tv_subtitle= (TextView) findViewById(R.id.tv_subtitle);
        tagViewPager = (TagViewPager) findViewById(R.id.tagViewPager);
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
