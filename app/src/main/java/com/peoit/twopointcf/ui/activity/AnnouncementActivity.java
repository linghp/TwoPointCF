package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.BusinessManagerPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;

/**
 * 添加项目公告(发布公告)
 * Created by zyz on 2015/10/28.
 */
public class AnnouncementActivity extends BaseActivity implements BusinessManagerPresenter.OnHttpResultListener{
    private EditText etAnnouncement1;
    private EditText etAnnouncement2;
    private TextView tvAnnouncement;
    private String noticeTitle, noticeContent;
    private BusinessManagerPresenter presenter;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
    }
    public static void startThisActivity(String id,Context context) {
        Intent intent = new Intent(context, AnnouncementActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
    @Override
    protected void initView() {
        etAnnouncement1 = (EditText) findViewById(R.id.et_announcement1);
        etAnnouncement2 = (EditText) findViewById(R.id.et_announcement2);
        tvAnnouncement = (TextView) findViewById(R.id.tv_announcement);
        tvAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (match()) {
                    params.put("noticeTitle", noticeTitle);
                    params.put("noticeContent", noticeContent);
                    params.put("projectId", id);//项目id
                    params.put("userId", localUserInfo.getUserId());
                    presenter.getData(URLs.PUBLISHREPORT,params);
                }
            }
        });
    }

    private boolean match() {
        noticeTitle = etAnnouncement1.getText().toString().trim();
        if (TextUtils.isEmpty(noticeTitle)) {
            showToast("请输入公告标题");
            return false;
        }
        noticeContent = etAnnouncement2.getText().toString().trim();
        if (TextUtils.isEmpty(noticeContent)) {
            showToast("请输入公告内容");
            return false;
        }
        return true;
    }

    @Override
    protected void initData() {
        presenter = new BusinessManagerPresenter(this);
        id=getIntent().getStringExtra("id");
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle("发布公告");
        }
    }

    @Override
    public void onHttpResultSuccess() {

    }
}
