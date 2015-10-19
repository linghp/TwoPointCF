package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;

import java.util.List;

/**
 * Created by ling on 2015/8/11.
 * last:2015/8/11
 * description:
 */
public class FollowProjectAdapter extends BaseAdapter {
    private Context context;
    private List<ProjectBean> items;
    private LayoutInflater mInflater;

    public FollowProjectAdapter(Context context, List<ProjectBean> items) {
        super();
        this.context = context;
        this.items = items;
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
        /*holder.iv_left.setImageResource(getItem(position).getPicture());
        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_content.setText(getItem(position).getMoney());
        holder.tv_tag.setText(getItem(position).getTime());*/
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