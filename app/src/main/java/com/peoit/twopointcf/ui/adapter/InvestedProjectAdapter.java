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
import com.peoit.twopointcf.ui.activity.MyPublishProjectActivity;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/8/11.
 * last:2015/8/11
 * description:
 */
public class InvestedProjectAdapter extends BaseAdapter {
    private Context context;
    private List<ProjectBean> items;
    private LayoutInflater mInflater;
    private Map<String,String> maps_status= MyPublishProjectActivity.maps_status;

    public InvestedProjectAdapter(Context context, List<ProjectBean> items) {
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
            holder.tv_tag = (TextView) convertView.findViewById(R.id.tv_tag);
            //convertView.findViewById(R.id.tv_tag).setVisibility(View.GONE);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_01);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_02);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_03);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(getItem(position).projectPhotos.size()>0) {
            Glide.with(context).load(URLs.HOST+getItem(position).projectPhotos.get(0)).into(holder.iv_left);
        }
        holder.tv_tag.setText(maps_status.get(getItem(position).status));
        holder.tv_title.setText(getItem(position).projectName);
        holder.tv_money.setText(CommonUtil.twoPointConversion(getItem(position).userInvestedAmount*getItem(position).perSellStockMoney/10000.0)+"万元");
        holder.tv_time.setText(getItem(position).endDate);
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
        public TextView tv_time;
        public TextView tv_title;
        public TextView tv_money;
    }
}
