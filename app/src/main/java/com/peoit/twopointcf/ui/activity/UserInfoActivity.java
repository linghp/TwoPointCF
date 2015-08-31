package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.modules.chooseimages.ChooseImages;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.FileUtil;

/**
 * 个人中心——个人简介
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener{
    private ImageView iv_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    protected void initView() {
        iv_photo= (ImageView) findViewById(R.id.iv_photo);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("马云");
        }
        if(localUserInfo!=null){
            String photoName=localUserInfo.getUserPhotoName();
            if(!TextUtils.isEmpty(photoName)){
                Bitmap bitmap = BitmapFactory.decodeFile(FileUtil.getImageDownloadDir(this)+ photoName);
                iv_photo.setImageBitmap(bitmap);
            }
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
        }
    }
}
