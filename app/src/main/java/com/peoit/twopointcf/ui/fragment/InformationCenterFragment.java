package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.InformationCenterBean;
import com.peoit.twopointcf.ui.activity.InformationCenterDetailActivity;
import com.peoit.twopointcf.ui.adapter.InformationCenterFragmentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ling
 *         资讯中心
 */
public class InformationCenterFragment extends BaseFragment implements AdapterView.OnItemClickListener,View.OnClickListener{
    private ListView listView;
    private InformationCenterFragmentAdapter adapter;
    private List<InformationCenterBean> informationCenterBeans=new ArrayList<>();

    public static InformationCenterFragment newInstance(int index) {
        InformationCenterFragment f = new InformationCenterFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_informationcenter, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.mylistview);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        generatedata();
        adapter=new InformationCenterFragmentAdapter(getActivity(),informationCenterBeans);
        listView.setAdapter(adapter);
    }

    private void generatedata() {
        informationCenterBeans.add(new InformationCenterBean("8.10","金佳俊宠物度假村","跟对方答复说分手费大幅度发鬼地方地方地方发放到地方地方的法规的地方改地方的法规"));
        informationCenterBeans.add(new InformationCenterBean("8.2","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
        informationCenterBeans.add(new InformationCenterBean("7.28","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
        informationCenterBeans.add(new InformationCenterBean("7.15","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
        informationCenterBeans.add(new InformationCenterBean("7.10","最惠宝","啥地方上电视上的的速度速度速度水电费水电费水电费水电费的发生的发生是的是的是的是的发生的发生的发生发生的发生的发生水电费水电费水电费"));
    }


    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.hideLeftBtn();
            titleView.setTitle(R.string.informationcenter);
//            Drawable drawable = getResources().getDrawable(R.mipmap.down_arrow);
//            titleView.setTitleRightDrawable(drawable,this);
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InformationCenterDetailActivity.startThisActivity(informationCenterBeans.get(position).getTitle(),
                informationCenterBeans.get(position).getContent(),informationCenterBeans.get(position).getTime(),getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_text:

                break;
        }
    }
}
