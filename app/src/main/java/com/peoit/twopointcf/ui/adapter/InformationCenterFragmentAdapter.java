package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.InformationCenterBean;

import java.util.List;

/**
 * Created by ling on 2015/8/5.
 * last:2015/8/5
 * description:
 */
public class InformationCenterFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<InformationCenterBean> items;
    private LayoutInflater mInflater;

    public InformationCenterFragmentAdapter(Context context, List<InformationCenterBean> items) {
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
            convertView = mInflater.inflate(R.layout.informationcenter_item_content, null);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_time.setText(getItem(position).getTime());
        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_content.setText(getItem(position).getContent());
        return convertView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public InformationCenterBean getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public static class ViewHolder {
        public TextView tv_time;
        public TextView tv_title;
        public TextView tv_content;
    }
}
