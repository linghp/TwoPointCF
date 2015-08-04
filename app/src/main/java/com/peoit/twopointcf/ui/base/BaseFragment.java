package com.peoit.twopointcf.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.view.TitleView;

/**
 * Created by ling on 2015/7/31.
 * last:2015/7/31
 * description:
 */
public class BaseFragment extends Fragment{
    protected View mParent;
    protected FragmentActivity mActivity;
    protected TitleView titleView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParent = getView();
        mActivity = getActivity();
        titleView= (TitleView) getView().findViewById(R.id.title_view);
    }
}
