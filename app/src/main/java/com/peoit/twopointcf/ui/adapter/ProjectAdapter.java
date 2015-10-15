package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.URLs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/8/11.
 * last:2015/8/11
 * description:
 */
public class ProjectAdapter extends BaseAdapter {
    private Context context;
    private List<ProjectBean> items = new ArrayList<>();
    private LayoutInflater mInflater;
    private Map<String,String> maps;

    public ProjectAdapter(Context context, List<ProjectBean> items,Map<String,String> maps) {
        super();
        this.context = context;
        this.items = items;
        this.maps=maps;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_investedproject, null);
            convertView.findViewById(R.id.ll_01).setVisibility(View.GONE);
            convertView.findViewById(R.id.ll_02).setVisibility(View.GONE);
            holder.iv_left = (ImageView) convertView.findViewById(R.id.iv_left);
            holder.tv_tag = (TextView) convertView.findViewById(R.id.tv_tag);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_01);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_04);
            holder.tv_content.setVisibility(View.VISIBLE);
            holder.tv_tag.setVisibility(View.VISIBLE);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.iv_left.setImageResource(getItem(position).getPicture());
        if(getItem(position).projectPhotos.size()>0) {
            Glide.with(context).load(URLs.HOST+getItem(position).projectPhotos.get(0)).into(holder.iv_left);
        }
        holder.tv_title.setText(getItem(position).projectName);
        holder.tv_content.setText(getItem(position).projectIntro);
        holder.tv_tag.setText(maps.get(getItem(position).status));
        return convertView;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public ProjectBean getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public static class ViewHolder {
        public ImageView iv_left;
        public TextView tv_tag;
        public TextView tv_title;
        public TextView tv_content;
    }
}
