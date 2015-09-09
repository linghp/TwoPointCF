package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.BusinessManagerBean;

import java.util.List;

/**
 * Created by ling on 2015/8/31.
 * description:
 */
public class InvestFindAdapter extends ArrayAdapter<BusinessManagerBean>{
    private LayoutInflater mInflater;
    private int resourceId;

    public InvestFindAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId=resource;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(resourceId, null);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_01);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_02);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.tv_title.setText(getItem(position).getTitle());
//        holder.tv_content.setText(getItem(position).getContent());
        return convertView;
    }

    public static class ViewHolder {
        public TextView tv_title;
        public TextView tv_content;
    }
}
