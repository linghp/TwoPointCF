package com.peoit.twopointcf.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.activity.BoundPhoneNumActivity;
import com.peoit.twopointcf.ui.activity.LoginActivity;
import com.peoit.twopointcf.ui.activity.MyRatingActivity;
import com.peoit.twopointcf.ui.activity.SecurityCenterActivity;
import com.peoit.twopointcf.ui.activity.SetUpActivity;
import com.peoit.twopointcf.ui.activity.UserInfoActivity;
import com.peoit.twopointcf.ui.activity.VerifiedActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.FileUtil;
import com.peoit.twopointcf.utils.MyLogger;

/**
 * @author ling
 *         个人中心
 */
public class MyCenterFragment extends BaseFragment {
    private TextView mycenter_tv1,mycenter_tv4,mycenter_tv2;
    private ImageView iv_photo;

    public static MyCenterFragment newInstance(int index) {
        MyCenterFragment f = new MyCenterFragment();

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
        View view = inflater.inflate(R.layout.fragment_mycenter, container, false);
        MyLogger.i("onCreateView");
        return view;
    }

    private void testGlide() {
        Glide.with(this).load("http://www.ainonggu666.com/upload/d92911e8-780a-4d2b-9b20-f84b9534afd6.gif").into(iv_photo);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyLogger.i("onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        if(localUserInfo!=null){
            String photoName=localUserInfo.getUserPhotoName();
            if(!TextUtils.isEmpty(photoName)){
                Bitmap bitmap = BitmapFactory.decodeFile(FileUtil.getImageDownloadDir(getActivity()) + photoName);
                iv_photo.setImageBitmap(bitmap);
            }
            //昵称
            mycenter_tv1.setText(localUserInfo.getUsername()+"");
            //绑定手机
            mycenter_tv4.setText(localUserInfo.getPhonenumber()+"");
            //个人简介
            mycenter_tv2.setText(localUserInfo.getuserCaption());
        }
        //testGlide();
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ll_mycenter01).setOnClickListener(this);
        view.findViewById(R.id.mycenter_ll2).setOnClickListener(this);
        view.findViewById(R.id.mycenter_ll3).setOnClickListener(this);
        view.findViewById(R.id.mycenter_ll4).setOnClickListener(this);
        view.findViewById(R.id.mycenter_ll7).setOnClickListener(this);
        view.findViewById(R.id.mycenter_ll8).setOnClickListener(this);
        iv_photo= (ImageView) view.findViewById(R.id.mycenter_iv);
        mycenter_tv1 = findViewByID_My(R.id.mycenter_tv1);
        mycenter_tv2 = findViewByID_My(R.id.mycenter_tv2);
        mycenter_tv4 = findViewByID_My(R.id.mycenter_tv4);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.hideLeftBtn();
            titleView.setTitle(R.string.mycenter);
            titleView.setRightBtn(R.mipmap.setting, this);
        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_btn:
                //设置
                CommonUtil.gotoActivity(getActivity(), SetUpActivity.class, false);
                return;
        }

        if (!localUserInfo.isLogin()){
            CommonUtil.gotoActivity(getActivity(),LoginActivity.class,false);
            return;
        }

        switch (v.getId()) {
            case R.id.ll_mycenter01:
                //个人简介
                CommonUtil.gotoActivity(getActivity(), UserInfoActivity.class, false);
                break;
            case R.id.mycenter_ll2:
                //实名认证
                CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class,false);
                break;
            case R.id.mycenter_ll3:
                //绑定手机
                CommonUtil.gotoActivity(getActivity(), BoundPhoneNumActivity.class,false);
                break;
            case R.id.mycenter_ll4:
                //授权密码
                SecurityCenterActivity.startThisActivity(false, getActivity());
                break;
            case R.id.mycenter_ll7:
                //我的等级
                CommonUtil.gotoActivity(getActivity(), MyRatingActivity.class, false);
                break;
            case R.id.mycenter_ll8:
                //安全中心
                SecurityCenterActivity.startThisActivity(true,getActivity());
                break;
            default:
                break;
        }
    }
}
