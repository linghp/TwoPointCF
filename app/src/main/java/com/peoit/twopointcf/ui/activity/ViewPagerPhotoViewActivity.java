/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.peoit.twopointcf.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.ui.view.HackyViewPager;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by ling on 2015/10/26.
 * description:可滑动放大的图片
 */

public class ViewPagerPhotoViewActivity extends AppCompatActivity {
    private PhotoViewAttacher mAttacher;
    private ViewPager mViewPager;
    private TextView tv_currentPosition;
    private ArrayList<String> imgList;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        tv_currentPosition = (TextView) findViewById(R.id.tv_currentPosition);
        //setContentView(mViewPager);
        imgList=getIntent().getStringArrayListExtra("imgList");
        position=getIntent().getIntExtra("position",0);
        if(imgList!=null){
            tv_currentPosition.setText(position+1+"/"+imgList.size());
            mViewPager.setAdapter(new SamplePagerAdapter());
            mViewPager.setCurrentItem(position);
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_currentPosition.setText(position + 1 + "/" + imgList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public static void startThisActivity(ArrayList<String> imgList,int position,Activity context) {
        Intent intent = new Intent(context, ViewPagerPhotoViewActivity.class);
        intent.putStringArrayListExtra("imgList", imgList);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


      class SamplePagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return imgList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(ViewPagerPhotoViewActivity.this).load(URLs.HOST + imgList.get(position)).placeholder(R.mipmap.placeholderpic).crossFade().into(photoView);
            mAttacher = new PhotoViewAttacher(photoView);
            mAttacher.setOnPhotoTapListener(new PhotoTapListener());
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {

        @Override
        public void onPhotoTap(View view, float x, float y) {
            ViewPagerPhotoViewActivity.this.finish();
        }
    }
}
