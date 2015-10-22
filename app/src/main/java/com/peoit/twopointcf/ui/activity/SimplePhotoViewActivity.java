package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.utils.MyLogger;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 点击查看项目详情的图片
 */
public class SimplePhotoViewActivity extends BaseActivity {
    private PhotoViewAttacher mAttacher;
    private String title,detail;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_photo_view);
    }

    public static void startThisActivity(String title,String detail, Context context) {
        Intent intent = new Intent(context, SimplePhotoViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("detail", detail);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mImageView = (ImageView) findViewById(R.id.iv_photo);
    }

    @Override
    protected void initData() {
        title=getIntent().getStringExtra("title");
        detail = getIntent().getStringExtra("detail");

        MyLogger.i(">>>>>>>"+title+":"+detail);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(title);
        if (!"".equals(detail)){
            Glide.with(this).load(URLs.HOST + detail).into(mImageView);
        }else {
            Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
            mImageView.setImageDrawable(bitmap);
            // The MAGIC happens here!
            mAttacher = new PhotoViewAttacher(mImageView);

            // Lets attach some listeners, not required though!
            mAttacher.setOnPhotoTapListener(new PhotoTapListener());
            //mAttacher.setScaleType(ImageView.ScaleType.CENTER);
        }
    }

    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {

        @Override
        public void onPhotoTap(View view, float x, float y) {
            SimplePhotoViewActivity.this.finish();
        }
    }
}
