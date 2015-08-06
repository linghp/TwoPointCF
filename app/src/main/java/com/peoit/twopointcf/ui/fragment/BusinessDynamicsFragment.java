package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseFragment;

/**
 * @author ling
 *	经营动态
 */
public class BusinessDynamicsFragment extends BaseFragment {
	
	private View mParent;
	
	private FragmentActivity mActivity;

	public static BusinessDynamicsFragment newInstance(int index) {
		BusinessDynamicsFragment f = new BusinessDynamicsFragment();

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
				.inflate(R.layout.fragment_businessdynamics, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mParent = getView();
		mActivity = getActivity();
        if(titleView!=null){
            titleView.hideLeftBtn();
            titleView.setTitle(R.string.businessdynamics);
        }

	}

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {

    }

}
