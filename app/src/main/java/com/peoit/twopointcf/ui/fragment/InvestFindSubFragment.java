package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.activity.CityActivity;
import com.peoit.twopointcf.ui.activity.InvestFindDetailActivity;
import com.peoit.twopointcf.ui.activity.SearchActivity;
import com.peoit.twopointcf.ui.adapter.InvestFindSubFragmentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.view.TagViewPager;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ling
 *         投资发现
 */
public class InvestFindSubFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private TextView mText;
    private TagViewPager tagViewPager;
    private ListView listView;
    private InvestFindSubFragmentAdapter adapter;

    public static InvestFindSubFragment newInstance(int index) {
        InvestFindSubFragment f = new InvestFindSubFragment();

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
        View view = inflater.inflate(R.layout.fragment_investfindsub, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        adapter = new InvestFindSubFragmentAdapter(getActivity());
//        ImageView imageView=new ImageView(this.getActivity());
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = CommonUtil.getScreenWidth(getActivity());
        layoutParams.height = layoutParams.width / 3;
//        imageView.setLayoutParams(layoutParams);
//        imageView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
//        imageView.setImageResource(R.mipmap.raw_1433489820);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        listView.addHeaderView(imageView);
        tagViewPager = new TagViewPager(getActivity());
        tagViewPager.setLayoutParams(layoutParams);
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    protected void initData() {
        //轮播
        List<Integer> imgLists = new ArrayList<>();
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        imgLists.add(R.mipmap.raw_1433489820);
        tagViewPager.toUse(imgLists, this);
        listView.addHeaderView(tagViewPager);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle(R.string.investfind);
            titleView.setRightBtn(R.mipmap.search_white, this);
            titleView.showLeftTextview("重庆", this);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            CommonUtil.gotoActivityWithData(getActivity(), InvestFindDetailActivity.class, bundle, false);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int tag = (int) v.getTag();
            switch (tag) {//轮播图的每一张的tag
                case 0:
                    // myToast(tag);
                case 1:
                    // myToast(tag);
                case 2:
                    //myToast(tag);
                case 3:
                    myToast(tag + "");
                    break;
            }
        } else {
            switch (v.getId()) {
                case R.id.right_btn:
                    CommonUtil.gotoActivity(getActivity(), SearchActivity.class,false);
                    break;
                case R.id.left_text:
                    CommonUtil.gotoActivity(getActivity(), CityActivity.class,false);
                    break;

            }
        }
    }
}
