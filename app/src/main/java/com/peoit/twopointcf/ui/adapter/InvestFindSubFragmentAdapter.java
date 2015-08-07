package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        return 3;
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
        ViewHolder viewHolder=null;
        if(convertView==null){
            view= LayoutInflater.from(context).inflate(
                    R.layout.fragment_investfindsubitem, null);
            viewHolder=new ViewHolder();
            viewHolder.iv_tag= (ImageView) view.findViewById(R.id.iv_tag);
            viewHolder.progressBar= (ProgressBar) view.findViewById(R.id.progressBar);
            viewHolder.view_normal=view.findViewById(R.id.ll_top_normal);
            viewHolder.view_complete=view.findViewById(R.id.rl_top_complete);
            viewHolder.tv_bottom01= (TextView) view.findViewById(R.id.tv_bottom01);
            viewHolder.tv_bottom02= (TextView) view.findViewById(R.id.tv_bottom02);
            viewHolder.tv_bottom03= (TextView) view.findViewById(R.id.tv_bottom03);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        if(position==0){
            viewHolder.iv_tag.setImageResource(R.mipmap.financing);
            viewHolder.progressBar.setProgress(65);
            viewHolder.view_normal.setVisibility(View.VISIBLE);
            viewHolder.view_complete.setVisibility(View.GONE);
        }else if(position==1){
            viewHolder.iv_tag.setImageResource(R.mipmap.preheating);
            viewHolder.progressBar.setProgress(0);
            viewHolder.tv_bottom01.setText("0%");
            viewHolder.tv_bottom02.setText("0万");
            viewHolder.tv_bottom03.setText("0天");
            viewHolder.view_normal.setVisibility(View.VISIBLE);
            viewHolder.view_complete.setVisibility(View.GONE);
        }else if(position==2){
            //viewHolder.iv_tag.setImageResource(R.mipmap.complete);
            viewHolder.progressBar.setProgress(100);
            viewHolder.tv_bottom01.setText("100%");
            viewHolder.tv_bottom02.setText("300万");
            viewHolder.tv_bottom03.setText("0天");
            viewHolder.view_normal.setVisibility(View.GONE);
            viewHolder.view_complete.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public class ViewHolder{
        public ProgressBar progressBar;
        public ImageView iv_tag;
        public View view_normal;
        public View view_complete;
        public TextView tv_bottom01;
        public TextView tv_bottom02;
        public TextView tv_bottom03;
    }
}
