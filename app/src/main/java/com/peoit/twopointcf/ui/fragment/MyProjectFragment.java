package com.peoit.twopointcf.ui.fragment;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.activity.BusinessManagerActivity;
import com.peoit.twopointcf.ui.activity.FollowProjectActivity;
import com.peoit.twopointcf.ui.activity.InvestedProjectActivity;
import com.peoit.twopointcf.ui.activity.LoginActivity;
import com.peoit.twopointcf.ui.activity.MyPublishProjectActivity;
import com.peoit.twopointcf.ui.activity.PublishProjectActivity;
import com.peoit.twopointcf.ui.activity.VerifiedActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.DialogTool;

/**
 * @author ling
 *         我的项目
 */
public class MyProjectFragment extends BaseFragment implements View.OnClickListener {

    private TextView mText;
    private int[] appCompatButton_ids;


    public static MyProjectFragment newInstance(int index) {
        MyProjectFragment f = new MyProjectFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myproject, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (titleView != null) {
            titleView.hideLeftBtn();
            titleView.setTitle(R.string.myproject);
//            titleView.setRightBtn(R.mipmap.add, this);
        }
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ll_01).setOnClickListener(this);
        view.findViewById(R.id.ll_02).setOnClickListener(this);
        view.findViewById(R.id.ll_03).setOnClickListener(this);
        view.findViewById(R.id.ll_04).setOnClickListener(this);
        view.findViewById(R.id.ll_05).setOnClickListener(this);
        //tint
        appCompatButton_ids= new int[]{R.id.ab_01,R.id.ab_02,R.id.ab_03,R.id.ab_04,R.id.ab_05};
        for (int appCompatButton_id : appCompatButton_ids) {
            AppCompatButton mBtnCompat = (AppCompatButton) view.findViewById(appCompatButton_id);
            ColorStateList lists = getResources().getColorStateList(R.color.lightblue500);
            mBtnCompat.setSupportBackgroundTintList(lists);
            mBtnCompat.setSupportBackgroundTintMode(PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (!localUserInfo.isLogin()) {
            CommonUtil.gotoActivity(getActivity(), LoginActivity.class, false);
            return;
        }
        switch (view.getId()) {
            /*case R.id.right_btn://title 右侧按钮(发布项目)
                if ("已认证".equals(localUserInfo.getIsrealnamevalidated())) {
                    CommonUtil.gotoActivity(getActivity(), PublishProjectActivity.class, false);
                } else if ("立即认证".equals(localUserInfo.getIsrealnamevalidated())) {
                    DialogTool.createCommonDialog(getActivity(), R.mipmap.ic_launcher, "发布项目", "您还没有实名认证，不能发布项目，立即实名认证？", "确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class, false);
                        }
                    }, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                        myToast("取消");
                        }
                    }).show();
                } else if ("审核中".equals(localUserInfo.getIsrealnamevalidated())) {
                    myToast("实名认证审核中，请耐心等待");
                }

                break;*/
            case R.id.ll_01:
                //已投项目
                CommonUtil.gotoActivity(getActivity(), InvestedProjectActivity.class, false);
                break;
            case R.id.ll_02:
                //关注项目
                CommonUtil.gotoActivity(getActivity(), FollowProjectActivity.class, false);
                break;
            case R.id.ll_03:
                //经营管理
                CommonUtil.gotoActivity(getActivity(), BusinessManagerActivity.class, false);
                break;
            case R.id.ll_04:
                //已发项目
                CommonUtil.gotoActivity(getActivity(), MyPublishProjectActivity.class, false);
                break;
            case R.id.ll_05:
                //发布项目
                if ("已认证".equals(localUserInfo.getIsrealnamevalidated())) {
                    CommonUtil.gotoActivity(getActivity(), PublishProjectActivity.class, false);
                } else if ("立即认证".equals(localUserInfo.getIsrealnamevalidated())) {
                    DialogTool.createCommonDialog(getActivity(), R.mipmap.ic_launcher, "发布项目", "您还没有实名认证，不能发布项目，立即实名认证？", "确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class, false);
                        }
                    }, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                        myToast("取消");
                        }
                    }).show();
                } else if ("审核中".equals(localUserInfo.getIsrealnamevalidated())) {
                    myToast("实名认证审核中，请耐心等待");
                }
                break;
        }

    }
}
