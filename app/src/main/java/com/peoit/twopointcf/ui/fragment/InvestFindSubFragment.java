package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.activity.InvestFindDetail;
import com.peoit.twopointcf.ui.adapter.InvestFindSubFragmentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * @author ling
 * 个人中心
 */
public class InvestFindSubFragment extends BaseFragment implements AdapterView.OnItemClickListener{
	private TextView mText;
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
        initView(view);
        adapter=new InvestFindSubFragmentAdapter(getActivity());
        ImageView imageView=new ImageView(this.getActivity());
        AbsListView.LayoutParams layoutParams=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width= CommonUtil.getScreenWidth(getActivity());
        layoutParams.height = layoutParams.width / 3;
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        imageView.setImageResource(R.mipmap.raw_1433489820);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        listView.addHeaderView(imageView);
        listView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        if(titleView!=null){
            titleView.hideLeftBtn();
        }
	}
    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {

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
        if(position!=0){
            Bundle bundle=new Bundle();
            bundle.putInt("position",position);
            CommonUtil.gotoActivityWithData(getActivity(), InvestFindDetail.class,bundle,false);
        }
    }
}
