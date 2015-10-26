package com.peoit.twopointcf.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.modules.chooseimages.ChooseImages;
import com.peoit.twopointcf.presenters.impl.PublishProjectPresenter;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 实名认证
 * Created by zyz on 2015/8/28.
 */
public class VerifiedActivity extends BaseActivity implements View.OnClickListener,PublishProjectPresenter.OnHttpResultListener {
    private EditText et_name;
    private EditText et_num;
    private EditText et_money;
    private EditText et_earnings;//收益
    private EditText et_name1;
    private EditText et_phone;
    private ImageView iv_photo1;
    private ImageView iv_photo2;
    private TextView tv_define;//确定
    private int iv_tag = 1;

    private PublishProjectPresenter presenter;
    public HashMap<String, String> params = new HashMap<>();
    public ArrayList<String> listFileNames = new ArrayList<>();
    public ArrayList<File> listFiles = new ArrayList<>();
    String realName,idNumber,fixedAssets,annualEarning,emergencyContactPersonName,emergencyContactPersonNumber;

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
        presenter = new PublishProjectPresenter(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
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
                    /*Uri uri = Uri.parse("");
                    uri = Uri.fromFile(new File(ChooseImages.imagelocaldir, ChooseImages.imageName));
                    File file = new File(FileUtil.getPath(this, uri));
                    listFiles.add(file);*/
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
                            listFileNames.add("idFrontFile");
                            Uri uri = Uri.parse("");
                            uri = Uri.fromFile(new File(ChooseImages.imagelocaldir, ChooseImages.imageName));
                            File file = new File(FileUtil.getPath(this, uri));
                            listFiles.add(file);
                            break;
                        case 2:
                            iv_photo2.setImageBitmap(bitmap);
                            listFileNames.add("idBackFile");
                            Uri uri1 = Uri.parse("");
                            uri1 = Uri.fromFile(new File(ChooseImages.imagelocaldir, ChooseImages.imageName));
                            File file1 = new File(FileUtil.getPath(this,uri1));
                            listFiles.add(file1);
                            break;
                    }
                    //updatePhotoInServer(imageName);
                    break;
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verified_tv1:
//                myToast("确定");
                /*listFiles.clear();
                listFileNames.clear();*/
                if (match()){
                    if (listFileNames.size() > 1  || listFiles.size() > 1) {
                        final String[] filenames = listFileNames.toArray(new String[0]);
                        final File[] files = listFiles.toArray(new File[0]);
                        params.put("id", localUserInfo.getUserId());
                        params.put("realName", realName);
                        params.put("idNumber", idNumber);
                        params.put("fixedAssets", fixedAssets);
                        params.put("annualEarning", annualEarning);
                        params.put("emergencyContactPersonName", emergencyContactPersonName);
                        params.put("emergencyContactPersonNumber", emergencyContactPersonNumber);
                        presenter.getVerified(filenames, files, params);
                    }else {
                        myToast("请上传身份证正反面");
                    }
                }
                break;
            case R.id.verified_iv1:
                ChooseImages.showPhotoDialog(this);
                iv_tag = 1;
                break;
            case R.id.verified_iv2:
                iv_tag = 2;
                ChooseImages.showPhotoDialog(this);
                break;
            default:
                break;
        }
    }

    private boolean match() {
        realName = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(realName)){
            myToast("请输入您的真实姓名");
            return false;
        }
        idNumber = et_num.getText().toString().trim();
        if (TextUtils.isEmpty(idNumber)){
            myToast("请输入您的身份证号");
            return false;
        }
        fixedAssets = et_money.getText().toString().trim();
        if (TextUtils.isEmpty(fixedAssets)){
            myToast("请输入您的固定资产");
            return false;
        }else {
            float i = 0;
            i = Float.valueOf(fixedAssets);
            if (i < 1000000){
                myToast("固定资产不能小于100万");
                return false;
            }
        }
        annualEarning = et_earnings.getText().toString().trim();
        if (TextUtils.isEmpty(annualEarning)){
            myToast("请输入您的年收益");
            return false;
        }else {
            float i = 0;
            i = Float.valueOf(annualEarning);
            if (i < 500000){
                myToast("流动资产不能小于50万");
                return false;
            }
        }
        emergencyContactPersonName = et_name1.getText().toString().trim();
        if (TextUtils.isEmpty(emergencyContactPersonName)){
            myToast("请输入紧急联系人姓名");
            return false;
        }
        emergencyContactPersonNumber = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(emergencyContactPersonNumber)){
            myToast("请输入紧急联系人电话");
            return false;
        }
        return true;
    }

    @Override
    public void onHttpResultSuccess() {

    }
}
