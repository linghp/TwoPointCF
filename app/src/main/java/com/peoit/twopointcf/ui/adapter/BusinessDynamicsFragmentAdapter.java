package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.BusinessDynamicsBean;
import com.peoit.twopointcf.ui.view.PinnedSectionListView;

import java.util.List;

/**
 * Created by ling on 2015/8/5.
 * last:2015/8/5
 * description:
 */
public class BusinessDynamicsFragmentAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
    private Context context;
    private List<BusinessDynamicsBean> items;
    private LayoutInflater mInflater;

    public BusinessDynamicsFragmentAdapter(Context context, List<BusinessDynamicsBean> items) {
        super();
        this.context=context;
        this.items=items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case BusinessDynamicsBean.SECTION:
                    convertView = mInflater.inflate(R.layout.item_section, null);
                    holder.tv_time = (TextView)convertView.findViewById(R.id.textView1);
                    break;
                case BusinessDynamicsBean.ITEM:
                    convertView = mInflater.inflate(R.layout.businessdynamics_item_content, null);
                    holder.tv_time = (TextView)convertView.findViewById(R.id.textView1);
                    holder.tv_title = (TextView)convertView.findViewById(R.id.tv_title);
                    holder.tv_content = (TextView)convertView.findViewById(R.id.tv_content);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv_time.setText(items.get(position).getTime());
        if(type==BusinessDynamicsBean.ITEM){
            holder.tv_title.setText(getItem(position).getTitle());
            holder.tv_content.setText(getItem(position).getContent());
        }
        return convertView;
    }

    @Override public int getViewTypeCount() {
        return 2;
    }

    @Override public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == BusinessDynamicsBean.SECTION;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public BusinessDynamicsBean getItem(int position) {
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
