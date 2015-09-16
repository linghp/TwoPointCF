package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub1Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub2Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub3Fragment;
import com.peoit.twopointcf.ui.fragment.InvestFindDetailSub4Fragment;
import com.peoit.twopointcf.utils.CommonUtil;

public class MyPublishDetailActivity extends BaseActivity {
    private BaseFragment firstFragment, secondFragment, thirdFragment, fourthFragment;
    private TextView tv_bottom01,tv_bottom02;
    private String title,statusvalue;
    private String[] published_statuss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish_detail);
    }

    public static void startThisActivity(String title, String statusvalue,Context context) {
        Intent intent = new Intent(context, MyPublishDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("statusvalue", statusvalue);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        findViewById(R.id.slide_tag1).setActivated(true);
        tv_bottom01= (TextView) findViewById(R.id.tv_bottom01);
        tv_bottom02= (TextView) findViewById(R.id.tv_bottom02);
    }

    @Override
    protected void initData() {
        published_statuss=this.getResources().getStringArray(R.array.published_status);
        firstFragment = new InvestFindDetailSub1Fragment();
        secondFragment = new InvestFindDetailSub2Fragment();
        thirdFragment = new InvestFindDetailSub3Fragment();
        fourthFragment = new InvestFindDetailSub4Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, firstFragment, "firstFragment").commit();

        title = getIntent().getStringExtra("title");
        statusvalue = getIntent().getStringExtra("statusvalue");
    }

    @Override
    protected void updateView() {
        if (!TextUtils.isEmpty(title)) {
            titleView.setTitle(title);
        }
        if (!TextUtils.isEmpty(statusvalue)) {
            ((TextView)findViewById(R.id.tv_status)).setText(statusvalue);
        }

        for (int i = 0; i < published_statuss.length; i++) {
            String status=published_statuss[i];
            if(status.equals(statusvalue)){
                switch (i) {
                    case 0:

                        break;
                    case 1:
                        tv_bottom02.setVisibility(View.VISIBLE);
                        tv_bottom02.setText("支付保证金");
                        tv_bottom02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myToast("支付保证金");
                            }
                        });
                        break;
                    case 2:
                        tv_bottom01.setVisibility(View.VISIBLE);
                        tv_bottom01.setText("开始众筹");
                        tv_bottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myToast("开始众筹");
                            }
                        });
                        tv_bottom02.setVisibility(View.VISIBLE);
                        tv_bottom02.setText("取消发布");
                        tv_bottom02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myToast("取消发布");
                            }
                        });
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:
                        tv_bottom01.setVisibility(View.VISIBLE);
                        tv_bottom01.setText("延期众筹");
                        tv_bottom01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myToast("延期众筹");
                            }
                        });
                        tv_bottom02.setVisibility(View.VISIBLE);
                        tv_bottom02.setText("取消众筹");
                        tv_bottom02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myToast("取消众筹");
                            }
                        });
                        break;
                    case 7:
                        titleView.showRightTextview("修改", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PublishProjectActivity.startThisActivity(true,MyPublishDetailActivity.this);
                            }
                        });
                        break;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
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
                CommonUtil.gotoActivity(this, InvestActivity.class, false);
                break;
        }
    }
}
