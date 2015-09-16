package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.modules.chooseimages.ChooseImages;
import com.peoit.twopointcf.ui.base.BaseActivity;

import java.io.File;

/**
 * 实名认证
 * Created by zyz on 2015/8/28.
 */
public class VerifiedActivity extends BaseActivity implements View.OnClickListener{
    private EditText et_name;
    private EditText et_num;
    private EditText et_money;
    private EditText et_earnings;//收益
    private EditText et_name1;
    private EditText et_phone;
    private ImageView iv_photo1;
    private ImageView iv_photo2;
    private TextView tv_define;//确定
    private int iv_tag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
    }

    @Override
    protected void initView() {
        et_name = (EditText) findViewById(R.id.verified_et1);
        et_num = (EditText) findViewById(R.id.verified_et2);
        et_money = (EditText) findViewById(R.id.verified_et3);
        et_earnings = (EditText) findViewById(R.id.verified_et4);
        et_name1 = (EditText) findViewById(R.id.verified_et5);
        et_phone = (EditText) findViewById(R.id.verified_et6);
        iv_photo1 = (ImageView) findViewById(R.id.verified_iv1);
        iv_photo2 = (ImageView) findViewById(R.id.verified_iv2);
        tv_define = (TextView) findViewById(R.id.verified_tv1);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if(titleView!=null) {
            titleView.setTitle("实名认证");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ChooseImages.PHOTO_REQUEST_TAKEPHOTO:
                    ChooseImages.startPhotoZoom(
                            Uri.fromFile(new File(ChooseImages.imagelocaldir, ChooseImages.imageName)), 480, this);
                    break;

                case ChooseImages.PHOTO_REQUEST_GALLERY:
                    if (data != null)
                        ChooseImages.startPhotoZoom(data.getData(), 480, this);
                    break;

                case ChooseImages.PHOTO_REQUEST_CUT:
                    // BitmapFactory.Options options = new BitmapFactory.Options();
                    //
                    // /**
                    // * 最关键在此，把options.inJustDecodeBounds = true;
                    // * 这里再decodeFile()，返回的bitmap为空
                    // * ，但此时调用options.outHeight时，已经包含了图片的高了
                    // */
                    // options.inJustDecodeBounds = true;\
                    Bitmap bitmap = BitmapFactory.decodeFile(ChooseImages.imagelocaldir
                            + ChooseImages.imageName);
                    //LocalUserInfo.getInstance(activity).setUserPhotoName(imageName);
                    switch (iv_tag) {
                        case 1:
                            iv_photo1.setImageBitmap(bitmap);
                            break;
                        case 2:
                            iv_photo2.setImageBitmap(bitmap);
                            break;
                    }
                    //updatePhotoInServer(imageName);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.verified_tv1:
                myToast("确定");
                break;
            case R.id.verified_iv1:
                ChooseImages.showPhotoDialog(this);
                iv_tag=1;
                break;
            case R.id.verified_iv2:
                iv_tag=2;
                ChooseImages.showPhotoDialog(this);
                break;
            default:
                break;
        }
    }
}
