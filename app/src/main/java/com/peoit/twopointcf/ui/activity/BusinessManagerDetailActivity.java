package com.peoit.twopointcf.ui.activity;

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
import com.peoit.twopointcf.ui.base.BaseFragmentActivity;

/**
 * Created by ling on 2015/9/1.
 * description:（我的项目->）经营管理详情
 */
public class BusinessManagerDetailActivity extends BaseFragmentActivity implements View.OnClickListener{
    private PopupWindow popupWindow;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_manager_detail);
    }

    @Override
    protected void initView() {
        super.initView();
        initPopupWindow();
    }

    @Override
    protected void initData() {
        super.initData();
        mFragments = new Fragment[2];
        mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.fragment_ProjectAnnouncement);
        mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.fragment_FinancialReports);
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1]).show(mFragments[0]).commit();
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle(R.string.title_activity_business_manager_detail);
            Drawable drawable = getResources().getDrawable(R.mipmap.down_arrow);
            titleView.setTitleRightDrawable(drawable,this);
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
        }
    }
}
