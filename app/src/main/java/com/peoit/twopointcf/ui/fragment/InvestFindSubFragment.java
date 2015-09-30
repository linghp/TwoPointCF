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
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.presenters.impl.FindProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.IFindProject;
import com.peoit.twopointcf.ui.activity.CityActivity;
import com.peoit.twopointcf.ui.activity.InvestFindDetailActivity;
import com.peoit.twopointcf.ui.activity.MyPublishProjectActivity;
import com.peoit.twopointcf.ui.activity.SearchActivity;
import com.peoit.twopointcf.ui.adapter.ProjectAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.view.TagViewPager;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;
import com.peoit.twopointcf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ling
 *         投资发现
 */
public class InvestFindSubFragment extends BaseFragment implements AdapterView.OnItemClickListener, FindProjectPresenter.OnHttpResultListener,
        AbPullToRefreshView.OnFooterLoadListener,AbPullToRefreshView.OnHeaderRefreshListener {
    private TextView mText;
    private TagViewPager tagViewPager;
    private ListView listView;
    //private InvestFindSubFragmentAdapter adapter;
    private List<ProjectBean> projectBeans = new ArrayList<>();
    private ProjectAdapter projectAdapter;
    private IFindProject presenter;
    private Map<String, String> maps = new HashMap<>();

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

        pullview.setOnHeaderRefreshListener(this);
        pullview.setOnFooterLoadListener(this);
        listView.setOnItemClickListener(this);
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

        presenter=new FindProjectPresenter(this);
        projectAdapter = new ProjectAdapter(getActivity(), projectBeans, MyPublishProjectActivity.maps_status);
        listView.setAdapter(projectAdapter);

        //maps.put("publisherId", localUserInfo.getUserId());
        presenter.getData(maps, projectBeans);
        listView.setAdapter(projectAdapter);
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
            InvestFindDetailActivity.startThisActivity(projectBeans.get(position-1),getActivity());
           // CommonUtil.gotoActivityWithData(getActivity(), InvestFindDetailActivity.class, bundle, false);
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

    @Override
    public void onHttpResultSuccess() {
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpResult() {
        pullview.onHeaderRefreshFinish();
        pullview.onFooterLoadFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getDataMore(maps);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(maps,projectBeans);
    }
}
