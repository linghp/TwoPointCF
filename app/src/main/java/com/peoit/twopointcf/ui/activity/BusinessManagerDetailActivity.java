package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.other.ScrollAwareFABBehavior;
import com.peoit.twopointcf.ui.base.BaseFragmentActivity;
import com.peoit.twopointcf.ui.view.SlidingTabLayout.SlidingTabLayout_noViewpager;
import com.peoit.twopointcf.utils.MyLogger;

/**
 * Created by ling on 2015/9/1.
 * description:（我的项目->）经营管理详情
 */
public class BusinessManagerDetailActivity extends BaseFragmentActivity implements SlidingTabLayout_noViewpager.TabListener {
    private PopupWindow popupWindow;
    private Fragment[] mFragments;
    private boolean isPublished;
    private String title;
    private SlidingTabLayout_noViewpager tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_manager_detail);
    }

    public static void startThisActivity(boolean isPublished,String title,Context context) {
        Intent intent = new Intent(context, BusinessManagerDetailActivity.class);
        intent.putExtra("isPublished", isPublished);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        tabs=findViewByID_My(R.id.tabs);
        //initPopupWindow();
    }

    @Override
    protected void initData() {
        super.initData();
        isPublished=getIntent().getBooleanExtra("isPublished",false);
        title=getIntent().getStringExtra("title");
        MyLogger.i("title:"+title);

        mFragments = new Fragment[2];
        mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.fragment_ProjectAnnouncement);
//        if(!isPublished) {
//            ((ProjectAnnouncementFragment) mFragments[0]).hiddenFAB();
//        }
        mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.fragment_FinancialReports);
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1]).show(mFragments[0]).commit();

        tabs.init(getResources().getStringArray(R.array.business_manager_detail_subtitle));
        tabs.setTabListener(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle(title);
//            Drawable drawable = getResources().getDrawable(R.mipmap.down_arrow);
//            titleView.setTitleRightDrawable(drawable,this);
            if(isPublished){
                titleView.setRightBtn(R.mipmap.add,this);
            }
        }


    }

    private void initPopupWindow() {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(this);
        // 引入窗口配置文件
        View view = inflater.inflate(R.layout.businessmanagerdetail_popupwindow, null);
        // 创建popupwindow对象
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // popupWindow.setBackgroundDrawable(R.drawable.background);
        // popupWindow.setHeight(500);
        // 点击外边可消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击外边窗口消失
        popupWindow.setOutsideTouchable(true);
        // 设置可获得焦点
        popupWindow.setFocusable(true);
        // popupwindow是否消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
            }
        });
        // 得到popupwindow里面的控件
        final TextView tv_01 = (TextView) view.findViewById(R.id.tv_01);
        final TextView tv_02 = (TextView) view.findViewById(R.id.tv_02);
        final Drawable drawable_right = getResources().getDrawable(R.drawable.ico_make);
        tv_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
                if(titleView!=null){
                    titleView.setTitle(R.string.title_activity_business_manager_detail);
                    //titleView.setTitleRightDrawable();
                }
                tv_01.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable_right, null);
                tv_02.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                        .hide(mFragments[1]).show(mFragments[0]).commit();
            }
        });
        tv_02.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
                if(titleView!=null){
                    titleView.setTitle(R.string.financialreports);
                    //titleView.setTitleRightDrawable();
                }
                tv_01.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                tv_02.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable_right, null);
                getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                        .hide(mFragments[1]).show(mFragments[1]).commit();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.title_text:
                popupWindow.showAsDropDown(v);
                break;
            case R.id.right_btn:
                if(mFragments[0].isVisible()){
                    myToast("0");
                }else{
                    myToast("1");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScrollAwareFABBehavior.isdisplay=true;
    }

    @Override
    public void onTabSelected(int pos) {
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1]).show(mFragments[pos]).commit();
    }

    @Override
    public void onTabReSelected(int pos) {

    }
}
