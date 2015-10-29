package com.peoit.twopointcf.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.ProjectAnnouncementBean;

import java.util.List;

/**
 * Created by ling on 2015/8/31.
 * description:
 */
public class ProjectAnnouncementAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<ProjectAnnouncementBean> listData;

    public ProjectAnnouncementAdapter(Context context, List<ProjectAnnouncementBean> mList) {
        super();
        this.context = context;
        this.listData = mList;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {

        View view = View.inflate(viewGroup.getContext(),
                R.layout.item_projectannouncement, null);
        // 创建一个ViewHolder
        MViewHolder holder = new MViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((MViewHolder)viewHolder).tv_title.setText(listData.get(i).getNoticeTitle());
        ((MViewHolder)viewHolder).tv_content.setText(listData.get(i).getNoticeContent());
        ((MViewHolder)viewHolder).tv_time.setText(listData.get(i).getCreateData());
    }



    public class MViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_content;
        public TextView tv_time;

        public MViewHolder(View view) {
            super(view);
            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_content = (TextView) view.findViewById(R.id.tv_content);
            this.tv_time = (TextView) view.findViewById(R.id.tv_time);

        }
    }

}
