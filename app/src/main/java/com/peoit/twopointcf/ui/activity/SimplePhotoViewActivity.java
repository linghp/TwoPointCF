package com.peoit.twopointcf.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SimplePhotoViewActivity extends BaseActivity {
    private PhotoViewAttacher mAttacher;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_photo_view);
    }

    public static void startThisActivity(String title, Context context) {
        Intent intent = new Intent(context, SimplePhotoViewActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ImageView mImageView = (ImageView) findViewById(R.id.iv_photo);

        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        mImageView.setImageDrawable(bitmap);

        // The MAGIC happens here!
        mAttacher = new PhotoViewAttacher(mImageView);

        // Lets attach some listeners, not required though!
        mAttacher.setOnPhotoTapListener(new PhotoTapListener());
        //mAttacher.setScaleType(ImageView.ScaleType.CENTER);
    }

    @Override
    protected void initData() {
        title=getIntent().getStringExtra("title");
    }

    @Override
    protected void updateView() {
        titleView.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_photo_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {

        @Override
        public void onPhotoTap(View view, float x, float y) {
            SimplePhotoViewActivity.this.finish();
        }
    }
}
