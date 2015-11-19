package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.ui.activity.MyPublishProjectActivity;
import com.peoit.twopointcf.utils.AbDateUtil;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ling on 2015/8/31.
 * description:
 */
public class InvestFindAdapter extends BaseAdapter {
    private Context context;
    private List<ProjectBean> items = new ArrayList<>();
    private LayoutInflater mInflater;

    public InvestFindAdapter(Context context, List<ProjectBean> items) {
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
            convertView = mInflater.inflate(R.layout.item_investfindproject, null);
            holder.iv_left = (ImageView) convertView.findViewById(R.id.iv_left);
            holder.iv_tag = (ImageView) convertView.findViewById(R.id.iv_tag);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_01);
           // holder.tv_02 = (TextView) convertView.findViewById(R.id.tv_02);
            holder.tv_03 = (TextView) convertView.findViewById(R.id.tv_03);
            holder.tv_04 = (TextView) convertView.findViewById(R.id.tv_04);
            holder.tv_05 = (TextView) convertView.findViewById(R.id.tv_05);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            holder.tv_bottom = (TextView) convertView.findViewById(R.id.tv_bottom);
            holder.tv_top02 = (TextView) convertView.findViewById(R.id.tv_top02);
//            holder.tv_02.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//            holder.tv_02.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    MyLogger.i(""+event.getAction());
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//                            v.setActivated(true);
//                            break;
//                        case MotionEvent.ACTION_UP:
//                            v.setActivated(false);
//                            break;
//                    }
//                    return false;
//                }
//            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.iv_left.setImageResource(getItem(position).getPicture());
        if (getItem(position).projectPhotos.size() > 0) {
            Glide.with(context).load(URLs.HOST + getItem(position).projectPhotos.get(0)).placeholder(R.mipmap.placeholderpic).crossFade().into(holder.iv_left);
        }/*else {
            holder.iv_left.setBackgroundResource(R.mipmap.raw_1433491802);
        }*/
        holder.tv_title.setText(getItem(position).projectName);
        if (getItem(position).status.equals(MyPublishProjectActivity.WAITING_INVESTED)) {
            holder.iv_tag.setImageResource(R.mipmap.chouzizhong_3x);
        } else if (getItem(position).status.equals(MyPublishProjectActivity.PROJECT_SUCCESS)) {
            holder.iv_tag.setImageResource(R.mipmap.yichenggong_3x);
        }
        //holder.tv_02.setText(getItem(position).id);
        holder.tv_03.setText(getItem(position).sellStockMoney / 10000 + "万元");
        holder.tv_04.setText(getItem(position).perSellStockMoney + "元");
        holder.tv_05.setText(CommonUtil.twoPointConversion(getItem(position).investedAmount / 10000.0) + "万");

        double investedPercent = getItem(position).investedAmount / (getItem(position).sellStockMoney + 0.0);
        int investedPercent_int = (int) Math.round(investedPercent * 100);
        if(investedPercent_int>=100){
            holder.progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progressbar_mini_finished));
        }else{
            holder.progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progressbar_mini));
        }
        holder.progressBar.setProgress(investedPercent_int);
        holder.tv_top02.setText(investedPercent_int + "%");
        //holder.tv_bottom02.setText(CommonUtil.twoPointConversion(getItem(position).investedAmount / 10000.0) + "万");
        if (!TextUtils.isEmpty(getItem(position).endDate)) {
            Calendar c = new GregorianCalendar();
            int days = AbDateUtil.getOffectDay(AbDateUtil.getDateByFormat(getItem(position).endDate, "yyyy-MM-dd").getTime(), c.getTime().getTime());
            if (days > 0)
                holder.tv_bottom.setText("剩余时间："+days + "天");
            else
                holder.tv_bottom.setText("剩余时间："+"已结束");

        }

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
        public ImageView iv_left, iv_tag;
        public TextView tv_title, tv_02, tv_03, tv_04, tv_05;
        public ProgressBar progressBar;
        //public TextView tv_bottom01, tv_bottom02, tv_bottom03;
        public TextView tv_top02, tv_bottom;
    }
}
