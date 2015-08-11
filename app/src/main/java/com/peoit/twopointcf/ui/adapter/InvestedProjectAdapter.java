package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.InvestedProjectBean;

import java.util.List;

/**
 * Created by ling on 2015/8/11.
 * last:2015/8/11
 * description:
 */
public class InvestedProjectAdapter extends BaseAdapter {
    private Context context;
    private List<InvestedProjectBean> items;
    private LayoutInflater mInflater;

    public InvestedProjectAdapter(Context context, List<InvestedProjectBean> items) {
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
            holder.iv_left = (ImageView) convertView.findViewById(R.id.iv_left);
            //holder.iv_tag = (ImageView) convertView.findViewById(R.id.iv_tag);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_01);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_02);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_03);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv_left.setImageResource(getItem(position).getPicture());
        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_money.setText(getItem(position).getMoney());
        holder.tv_time.setText(getItem(position).getTime());
        return convertView;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public InvestedProjectBean getItem(int position) {
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
        //public ImageView iv_tag;
        public TextView tv_time;
        public TextView tv_title;
        public TextView tv_money;
    }
}
