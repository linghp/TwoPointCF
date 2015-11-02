package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

public class InformationCenterDetailActivity extends BaseActivity{
    private TextView tv_time,tv_content;
    private String id,title,informationIntro,content,picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_center_detail);
    }

    public static void startThisActivity(String id,String title, String informationIntro,String content,String picturePath, Context context) {
        Intent intent = new Intent(context, InformationCenterDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("informationIntro", informationIntro);
        intent.putExtra("content", content);
        intent.putExtra("picturePath", picturePath);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        tv_time=findViewByID_My(R.id.tv_time);
        tv_content=findViewByID_My(R.id.tv_content);
    }

    @Override
    protected void initData() {
        id=getIntent().getStringExtra("id");
        title=getIntent().getStringExtra("title");
        informationIntro = getIntent().getStringExtra("informationIntro");
        content=getIntent().getStringExtra("content");
        picturePath=getIntent().getStringExtra("picturePath");
    }

    @Override
    protected void updateView() {
        //tv_time.setText(id);
        tv_content.setText(content);
        titleView.setTitle(title);
        titleView.setRightBtn(R.mipmap.share_normal,this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.right_btn:
                myToast("分享");
                break;
        }
    }
}
