package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.activity.FollowProjectActivity;
import com.peoit.twopointcf.ui.activity.InvestedProjectActivity;
import com.peoit.twopointcf.ui.activity.MyPublishProjectActivity;
import com.peoit.twopointcf.ui.activity.PublishProjectActivity;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.utils.CommonUtil;

/**
 * @author ling
 * 个人中心
 */
public class MyProjectFragment extends BaseFragment implements View.OnClickListener{

	private TextView mText;


	public static MyProjectFragment newInstance(int index) {
		MyProjectFragment f = new MyProjectFragment();

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
		View view = inflater.inflate(R.layout.fragment_myproject, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        if(titleView!=null){
            titleView.hideLeftBtn();
            titleView.setTitle(R.string.myproject);
			titleView.setRightBtn(R.mipmap.add, this);
        }
	}

    @Override
    protected void initView(View view) {
      view.findViewById(R.id.ll_01).setOnClickListener(this);
      view.findViewById(R.id.ll_02).setOnClickListener(this);
      view.findViewById(R.id.ll_03).setOnClickListener(this);
      view.findViewById(R.id.ll_04).setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.right_btn://title 右侧按钮
                CommonUtil.gotoActivity(getActivity(), PublishProjectActivity.class, false);
                break;
            case R.id.ll_01:
                CommonUtil.gotoActivity(getActivity(), InvestedProjectActivity.class,false);
                break;
            case R.id.ll_02:
                CommonUtil.gotoActivity(getActivity(), FollowProjectActivity.class, false);
                break;
            case R.id.ll_03:
                myToast("03");
                break;
            case R.id.ll_04:
                CommonUtil.gotoActivity(getActivity(), MyPublishProjectActivity.class, false);
                break;

        }

    }
}
