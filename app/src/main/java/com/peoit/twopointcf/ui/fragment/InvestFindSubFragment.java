package com.peoit.twopointcf.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.entity.BannerBean;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.net.URLs;
import com.peoit.twopointcf.presenters.impl.FindProjectPresenter;
import com.peoit.twopointcf.presenters.interfaces.IFindProject;
import com.peoit.twopointcf.ui.activity.CityActivity;
import com.peoit.twopointcf.ui.activity.InvestFindDetailActivity;
import com.peoit.twopointcf.ui.activity.SearchActivity;
import com.peoit.twopointcf.ui.adapter.InvestFindAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.view.TagViewPager;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;
import com.peoit.twopointcf.utils.CommonUtil;
import com.peoit.twopointcf.utils.MyLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ling
 *         投资发现
 */
public class InvestFindSubFragment extends BaseFragment implements AdapterView.OnItemClickListener, FindProjectPresenter.OnHttpResultListener,
        AbPullToRefreshView.OnFooterLoadListener,AbPullToRefreshView.OnHeaderRefreshListener ,FindProjectPresenter.OnHttpResultBannerListener {
    private TextView mText;
    private TagViewPager tagViewPager;
    private ListView listView;
    //private InvestFindSubFragmentAdapter adapter;
    private List<ProjectBean> projectBeans = new ArrayList<>();
    private List<BannerBean> bannerBeans=new ArrayList<>();
    private InvestFindAdapter investFindAdapter;
    private IFindProject presenter;
    private Map<String, String> maps = new HashMap<>();
    public static final int RESULT_GET_CITY = 1000;
    private String city="";

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
//        List<Integer> imgLists = new ArrayList<>();
//        imgLists.add(R.mipmap.raw_1433489820);
//        imgLists.add(R.mipmap.raw_1433489820);
//        imgLists.add(R.mipmap.raw_1433489820);
//        imgLists.add(R.mipmap.raw_1433489820);
//        tagViewPager.toUse(imgLists, this);
        listView.addHeaderView(tagViewPager);

        presenter=new FindProjectPresenter(this);
        investFindAdapter = new InvestFindAdapter(getActivity(), projectBeans);

        maps.put("statusMark", "y");
        //presenter.getData(URLs.FINDPROJECT,maps, projectBeans);
        requestServer();
        listView.setAdapter(investFindAdapter);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle(R.string.investfind);
            titleView.setRightBtn(R.mipmap.search_white, this);
            titleView.showLeftTextview("全国", this);
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
//            Bundle bundle = new Bundle();
//            bundle.putInt("position", position);
            InvestFindDetailActivity.startThisActivity(projectBeans.get(position-1),false,getActivity());
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
                    //搜索
                    //CommonUtil.gotoActivity(getActivity(), SearchActivity.class,false);
                    SearchActivity.startThisActivity(city,getActivity());
                    break;
                case R.id.left_text:
                    //选择城市
                    CommonUtil.gotoActivityForResult_fragment(this,CityActivity.class,RESULT_GET_CITY,false);
                    break;

            }
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
        if(city.equals("全国")){
            maps.put("city", "");
        }else{
            maps.put("city", city);
        }
        presenter.getDataBanner(URLs.BANNERLIST,bannerBeans,this);
        presenter.getData(URLs.FINDPROJECT,maps, projectBeans);

    }

    @Override
    public void onHttpResultSuccess() {
        investFindAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpResultSuccessBanner() {
        List<String> imgList=new ArrayList<>();
        for (BannerBean bannerBean : bannerBeans) {
            imgList.add(bannerBean.getPath());
        }
        tagViewPager.toUse(imgList, this);
    }

    @Override
    public void onHttpResult() {
        pullview.onHeaderRefreshFinish();
        pullview.onFooterLoadFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getDataMore(URLs.FINDPROJECT,maps);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(URLs.FINDPROJECT,maps,projectBeans);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK){
            city=data.getStringExtra("city");
            MyLogger.i(city);
            titleView.showLeftTextview(city, this);
            requestServer();
        }
    }
}
