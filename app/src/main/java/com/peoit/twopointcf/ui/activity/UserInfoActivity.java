package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.modules.chooseimages.ChooseImages;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.FileUtil;

/**
 * 个人中心——个人简介
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener{
    private ImageView iv_photo;
    private TextView tv_userinfo1,tv_userinfo2,tv_userinfo3,tv_userinfo4,tv_userinfo5,tv_userinfo6,tv_userinfo7;
    private LinearLayout ll_info,ll_verified,ll_photonum,ll_mailbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    protected void initView() {
        iv_photo= (ImageView) findViewById(R.id.iv_photo);
        ll_info = (LinearLayout) findViewById(R.id.ll_userinfo05);
        ll_verified = (LinearLayout) findViewById(R.id.ll_userinfo06);
        ll_photonum = (LinearLayout) findViewById(R.id.ll_userinfo07);
        ll_mailbox = (LinearLayout) findViewById(R.id.ll_userinfo08);

        tv_userinfo1 = findViewByID_My(R.id.tv_userinfo1);
        tv_userinfo2 = findViewByID_My(R.id.tv_userinfo2);
        tv_userinfo3 = findViewByID_My(R.id.tv_userinfo3);
        tv_userinfo4 = findViewByID_My(R.id.tv_userinfo4);
        tv_userinfo5 = findViewByID_My(R.id.tv_userinfo5);
        tv_userinfo6 = findViewByID_My(R.id.tv_userinfo6);
        tv_userinfo7 = findViewByID_My(R.id.tv_userinfo7);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle(localUserInfo.getUsername());
        }else
            titleView.setTitle("用户简介");
        if(localUserInfo!=null){
            String photoName=localUserInfo.getUserPhotoName();
            if(!TextUtils.isEmpty(photoName)){
                Bitmap bitmap = BitmapFactory.decodeFile(FileUtil.getImageDownloadDir(this)+ photoName);
                iv_photo.setImageBitmap(bitmap);
            }
            tv_userinfo1.setText(localUserInfo.getUsername());//姓名
//            tv_userinfo2.setText(localUserInfo.getUsername());//性别
//            tv_userinfo3.setText(localUserInfo.getUsername());//等级
//            tv_userinfo4.setText(localUserInfo.getUsername());//个人简介
//            tv_userinfo5.setText(localUserInfo.getUsername());//实名认证
            tv_userinfo6.setText(localUserInfo.getPhonenumber());//手机号码
            tv_userinfo7.setText(localUserInfo.getEmail());//邮箱



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            ChooseImages.activityResultSwitch(requestCode,data,iv_photo,this);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_userinfo01:
                ChooseImages.showPhotoDialog(this);
                break;
            case R.id.ll_userinfo05:
                //个人简介
                CommonUtil.gotoActivity(this, PersonalProfileActivity.class, false);
                break;
            case R.id.ll_userinfo06:
                //实名认证
                CommonUtil.gotoActivity(this, VerifiedActivity.class, false);
                break;
            case R.id.ll_userinfo07:
                //手机号码
                CommonUtil.gotoActivity(this, ChangePhoneActivity.class,false);
                break;
            case R.id.ll_userinfo08:
                //邮箱
                CommonUtil.gotoActivity(this, ChangePhoneActivity.class,false);
                break;
            default:
                break;
        }
    }
}
