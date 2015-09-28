package com.peoit.twopointcf.ui.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.peoit.photochooser.ImagePagerActivity;
import com.peoit.photochooser.PhotoOperate;
import com.peoit.photochooser.PhotoPickActivity;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.MyLogger;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishFragment02 extends BaseFragment implements AdapterView.OnItemClickListener {

    public PublishFragment02() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publish_fragment02, container, false);
    }


    @Override
    protected void initView(View view) {
        gridView01 = findViewByID_My(R.id.gridView01);
        gridView02 = findViewByID_My(R.id.gridView02);
        gridView03 = findViewByID_My(R.id.gridView03);
        gridView04 = findViewByID_My(R.id.gridView04);

        imageWidthPx = 200;
        mSize = new ImageSize(imageWidthPx, imageWidthPx);
        gridView01.setAdapter(adapter1);
        gridView02.setAdapter(adapter2);
        gridView03.setAdapter(adapter3);
        gridView04.setAdapter(adapter4);

        gridView01.setOnItemClickListener(this);
        gridView02.setOnItemClickListener(this);
        gridView03.setOnItemClickListener(this);
        gridView04.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        photoOperate = new PhotoOperate(getActivity());
        for (int i = 0; i < 4; i++) {
            mDatas.add(new ArrayList());
        }

    }

    @Override
    protected void updateView() {

    }


    //-------------------------------以下为添加图片模块----------------------------------------------
    public static final int PHOTO_MAX_COUNT = 6;
    private GridView gridView01,gridView02,gridView03,gridView04;
    private int imageWidthPx;
    private ImageSize mSize;
    private PhotoOperate photoOperate;
    private Uri fileUri;
    private int tag=0;//判断点击的是一个gridview

    public ArrayList<ArrayList<PhotoData>> mDatas = new ArrayList(4);
    BaseAdapter adapter1 = new MyGridViewAdapter(mDatas.get(0));
    BaseAdapter adapter2 = new MyGridViewAdapter(mDatas.get(1));
    BaseAdapter adapter3 = new MyGridViewAdapter(mDatas.get(2));
    BaseAdapter adapter4 = new MyGridViewAdapter(mDatas.get(3));

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gridView01:
                tag=1;
                onItemClickExtract(position,mDatas.get(0));
                break;
            case R.id.gridView02:
                tag=2;
                onItemClickExtract(position,mDatas.get(1));
                break;
            case R.id.gridView03:
                tag=3;
                onItemClickExtract(position,mDatas.get(2));
                break;
            case R.id.gridView04:
                tag=4;
                onItemClickExtract(position,mDatas.get(3));
                break;
        }

    }

    private void onItemClickExtract(int position,ArrayList<PhotoData> mData) {
        if (position == mData.size()) {
            int count = PHOTO_MAX_COUNT - mData.size();
            if (count <= 0) {
                return;
            }

            Intent intent = new Intent(getActivity(),
                    PhotoPickActivity.class);
            intent.putExtra(PhotoPickActivity.EXTRA_MAX, count);
            startActivityForResult(intent, RESULT_REQUEST_PICK_PHOTO);

        } else {
            Intent intent = new Intent(getActivity(),
                    ImagePagerActivity.class);
            ArrayList<String> arrayUri = new ArrayList<String>();
            for (PhotoData item : mData) {
                arrayUri.add(item.uri.toString());
            }
            intent.putExtra("mArrayUri", arrayUri);
            intent.putExtra("mPagerPosition", position);
            intent.putExtra("needEdit", true);
            startActivityForResult(intent, RESULT_REQUEST_IMAGE);
        }
    }

    public class MyGridViewAdapter extends BaseAdapter {
        private ArrayList<PhotoData> photoDatas;

        public MyGridViewAdapter(ArrayList<PhotoData> photoDatas) {
            this.photoDatas = photoDatas;
        }

        public int getCount() {
            return photoDatas.size() + 1;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        ArrayList<ViewHolder> holderList = new ArrayList<ViewHolder>();

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                holder.image = (ImageView) LayoutInflater.from(getActivity()).inflate(
                        R.layout.image_make_maopao, parent, false);
                holderList.add(holder);
                holder.image.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == getCount() - 1) {
                if (getCount() == (PHOTO_MAX_COUNT + 1)) {
                    holder.image.setVisibility(View.INVISIBLE);

                } else {
                    holder.image.setVisibility(View.VISIBLE);
                    holder.image.setImageResource(R.drawable.addimage);
                    holder.uri = "";
                }

            } else {
                holder.image.setVisibility(View.VISIBLE);
                PhotoData photoData = photoDatas.get(position);
                Uri data = photoData.uri;
                holder.uri = data.toString();

                ImageLoader.getInstance().loadImage(data.toString(), mSize,
                        new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri,
                                                          View view, Bitmap loadedImage) {
                                for (ViewHolder item : holderList) {
                                    if (item.uri.equals(imageUri)) {
                                        item.image.setImageBitmap(loadedImage);
                                    }
                                }
                            }
                        });
            }

            return holder.image;
        }

        class ViewHolder {
            ImageView image;
            String uri = "";
        }
    }

    public static class PhotoData {
        public Uri uri = Uri.parse("");
        public String serviceUri = "";

        public PhotoData(File file) {
            uri = Uri.fromFile(file);
        }

        public PhotoData(PhotoDataSerializable data) {
            uri = Uri.parse(data.uriString);
            serviceUri = data.serviceUri;
        }
    }

    // 因为PhotoData包含Uri，不能直接序列化，所以有了这个类
    public static class PhotoDataSerializable implements Serializable {
        String uriString = "";
        String serviceUri = "";

        public PhotoDataSerializable(PhotoData data) {
            uriString = data.uri.toString();
            serviceUri = data.serviceUri;
        }

    }

    public static final int RESULT_REQUEST_IMAGE = 100;
    public static final int RESULT_REQUEST_FOLLOW = 1002;
    public static final int RESULT_REQUEST_PICK_PHOTO = 1003;
    public static final int RESULT_REQUEST_PHOTO = 1005;
    public static final int RESULT_REQUEST_LOCATION = 1006;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        MyLogger.i("onActivityResult");
        switch (tag) {
            case 1:
                onActivityResultExtract(requestCode, resultCode, data,mDatas.get(0),adapter1);
                break;
            case 2:
                onActivityResultExtract(requestCode, resultCode, data,mDatas.get(1),adapter2);
                break;
            case 3:
                onActivityResultExtract(requestCode, resultCode, data,mDatas.get(2),adapter3);
                break;
            case 4:
                onActivityResultExtract(requestCode, resultCode, data,mDatas.get(3),adapter4);
                break;
        }


    }

    private void onActivityResultExtract(int requestCode, int resultCode, Intent data,ArrayList<PhotoData> mData,BaseAdapter adapter) {
        if (requestCode == RESULT_REQUEST_PICK_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    ArrayList<PhotoPickActivity.ImageInfo> pickPhots = (ArrayList<PhotoPickActivity.ImageInfo>) data
                            .getSerializableExtra("data");
                    for (PhotoPickActivity.ImageInfo item : pickPhots) {
                        Uri uri = Uri.parse(item.path);
                        File outputFile = photoOperate.scal(uri);
                        mData.add(new PhotoData(outputFile));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == RESULT_REQUEST_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    File outputFile = photoOperate.scal(fileUri);
                    mData.add(mData.size(), new PhotoData(
                            outputFile));
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == RESULT_REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<String> delUris = data
                        .getStringArrayListExtra("mDelUrls");
                for (String item : delUris) {
                    for (int i = 0; i < mData.size(); ++i) {
                        if (mData.get(i).uri.toString().equals(item)) {
                            mData.remove(i);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        } else if (requestCode == RESULT_REQUEST_FOLLOW) {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getStringExtra("name");
                //mEnterLayout.insertText(name);
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
