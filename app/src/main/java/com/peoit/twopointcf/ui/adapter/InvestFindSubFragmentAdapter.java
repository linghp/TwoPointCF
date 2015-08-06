package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.peoit.twopointcf.R;

/**
 * Created by ling on 2015/8/5.
 * last:2015/8/5
 * description:
 */
public class InvestFindSubFragmentAdapter extends BaseAdapter{
    private Context context;

    public InvestFindSubFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        if(convertView==null){
            view= LayoutInflater.from(context).inflate(
                    R.layout.fragment_investfindsubitem, null);
        }else{
            view=convertView;
        }
        return view;
    }
}
