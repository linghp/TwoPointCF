package com.peoit.twopointcf.modules.chooseimages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.utils.FileUtil;
import com.peoit.twopointcf.utils.LocalUserInfo;
import com.peoit.twopointcf.utils.MyLogger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ling on 2015/8/24.
 * description:选择图片，通过拍照或者图片文件
 */
public class ChooseImages {
    public static String imagelocaldir;// 图片存储根路径
    public static String imageName;// 头像存储文件名

    private ImageView iv_loading;

    public static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 3;// 结果

    public static void showPhotoDialog(final Activity activity) {
        final AlertDialog dlg = new AlertDialog.Builder(activity).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.alertdialog);
        // 为确认按钮添加事件,执行退出应用操作
        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);

        imagelocaldir= FileUtil.getImageDownloadDir(activity);
        MyLogger.i(imagelocaldir);

        tv_paizhao.setText("拍照");
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            public void onClick(View v) {

                imageName = getNowTime() + ".png";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 指定调用相机拍照后照片的储存路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(imagelocaldir, imageName)));
                activity.startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                dlg.cancel();
            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText("相册");
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getNowTime();
                imageName = getNowTime() + ".png";
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                dlg.cancel();
            }
        });

    }

    private static String getNowTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmssSS");
        return dateFormat.format(date);
    }

    /**
     * 适用只有一张图片并保存在偏好里面，其他情况在调用方实现
     * @param requestCode
     * @param data
     * @param iv_photo
     * @param activity
     */
    public static void activityResultSwitch(int requestCode,Intent data,ImageView iv_photo, Activity activity){
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                startPhotoZoom(
                        Uri.fromFile(new File(imagelocaldir, imageName)), 480,activity);
                break;

            case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData(), 480,activity);
                break;

            case PHOTO_REQUEST_CUT:
                // BitmapFactory.Options options = new BitmapFactory.Options();
                //
                // /**
                // * 最关键在此，把options.inJustDecodeBounds = true;
                // * 这里再decodeFile()，返回的bitmap为空
                // * ，但此时调用options.outHeight时，已经包含了图片的高了
                // */
                // options.inJustDecodeBounds = true;\
                MyLogger.i(">>>>>>>>>>>图片路径"+imagelocaldir);
                Bitmap bitmap = BitmapFactory.decodeFile(imagelocaldir
                        + imageName);
                LocalUserInfo.getInstance(activity).setUserPhotoName(imageName); 
                iv_photo.setImageBitmap(bitmap);

                //updatePhotoInServer(imageName);
                break;
        }
    }

    public static void startPhotoZoom(Uri uri1, int size,Activity activity) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri1, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", false);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(imagelocaldir, imageName)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
