package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.adapter.InvestFindSubFragmentAdapter;
import com.peoit.twopointcf.ui.base.BaseFragment;

/**
 * @author ling
 * 个人中心
 */
public class InvestFindSubFragment extends BaseFragment {
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
        imageView.setImageResource(R.mipmap.ic_launcher);
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

}
