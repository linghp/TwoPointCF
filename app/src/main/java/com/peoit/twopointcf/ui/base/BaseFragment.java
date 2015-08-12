package com.peoit.twopointcf.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.view.TitleView;

/**
 * Created by ling on 2015/7/31.
 * last:2015/7/31
 * description:
 */
public abstract class BaseFragment extends Fragment {
    protected View mParent;
    protected Activity mActivity;
    protected TitleView titleView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent = getView();
        mActivity = getActivity();
        titleView= (TitleView) getView().findViewById(R.id.title_view);
        initView(mParent);
        initData();
        updateView();
    }

    protected abstract void initView(View view);
    protected abstract void initData();
    protected abstract void updateView();

    protected void myToast(String content){
        Toast.makeText(this.getActivity(),content,Toast.LENGTH_SHORT).show();
    }
}
