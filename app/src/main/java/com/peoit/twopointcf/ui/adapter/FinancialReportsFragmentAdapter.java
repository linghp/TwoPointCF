package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.FinancialReportsBean;
import com.peoit.twopointcf.ui.view.PinnedSectionListView;

import java.util.List;

/**
 * Created by ling on 2015/8/5.
 * last:2015/8/5
 * description:
 */
public class FinancialReportsFragmentAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
    private Context context;
    private List<FinancialReportsBean> items;
    private LayoutInflater mInflater;

    public FinancialReportsFragmentAdapter(Context context, List<FinancialReportsBean> items) {
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
                case FinancialReportsBean.SECTION:
                    convertView = mInflater.inflate(R.layout.item_section, null);
                    holder.tv01 = (TextView)convertView.findViewById(R.id.textView1);
                    break;
                case FinancialReportsBean.ITEM:
                    convertView = mInflater.inflate(R.layout.item_financialreports, null);
                    holder.tv01 = (TextView)convertView.findViewById(R.id.textView1);
                    holder.tv02 = (TextView)convertView.findViewById(R.id.textView2);
                    holder.tv03 = (TextView)convertView.findViewById(R.id.textView3);
                    holder.tv04 = (TextView)convertView.findViewById(R.id.textView4);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv01.setText(items.get(position).getTime());
        if(type==FinancialReportsBean.ITEM){
            holder.tv02.setText(getItem(position).getEarning());
            holder.tv03.setText(getItem(position).getExpenditure());
            holder.tv04.setText(getItem(position).getRemarks());
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
        return viewType == FinancialReportsBean.SECTION;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public FinancialReportsBean getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public static class ViewHolder {
        public TextView tv01,tv02,tv03,tv04;

    }
}
