package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.MyLogger;

/**
 * @author ling
 * 个人中心
 */
public class MyCenterFragment extends BaseFragment implements View.OnClickListener {
	private TextView mText;


	public static MyCenterFragment newInstance(int index) {
		MyCenterFragment f = new MyCenterFragment();

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
		View view = inflater.inflate(R.layout.fragment_mycenter, container, false);
        MyLogger.i("onCreateView");
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        MyLogger.i("onActivityCreated");
        updateView();
	}

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        if(titleView!=null){
            titleView.hideLeftBtn();
            titleView.setRightBtn(R.mipmap.setting,this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_btn:
                myToast("setting");
                break;
        }
    }
}
