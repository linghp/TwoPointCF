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
import com.peoit.twopointcf.entity.CommentBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.utils.MyLogger;

import java.util.List;

/**
 * Created by ling on 2015/8/11.
 * last:2015/8/11
 * description:
 */
public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentBean> items;
    private LayoutInflater mInflater;

    public CommentAdapter(Context context, List<CommentBean> items) {
        super();
        this.context = context;
        this.items = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyLogger.i("getView");
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_invest_find_detail_discussion, null);
            holder.iv_avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            holder.tv_discussantName = (TextView) convertView.findViewById(R.id.tv_discussantName);
            holder.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(URLs.HOST + getItem(position).getAvatar()).placeholder(R.mipmap.defaultloginheader).crossFade().into(holder.iv_avatar);
        holder.tv_discussantName.setText(getItem(position).getDiscussantName());
        holder.tv_comment.setText(getItem(position).getComments());
        return convertView;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public CommentBean getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public static class ViewHolder {
        public ImageView iv_avatar;
        public TextView tv_discussantName;
        public TextView tv_comment;
    }
}
