package com.peoit.twopointcf.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.view.TitleView;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;
import com.peoit.twopointcf.utils.LocalUserInfo;

/**
 * Created by ling on 2015/7/31.
 * last:2015/7/31
 * description:
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected View mParent;
    protected Activity mActivity;
    protected TitleView titleView;
    protected AbPullToRefreshView pullview;
    protected LocalUserInfo localUserInfo;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("BaseFragment", getClass().getSimpleName());
        mParent = getView();
        mActivity = getActivity();
        titleView= (TitleView) getView().findViewById(R.id.title_view);
        localUserInfo = LocalUserInfo.getInstance(getActivity());
        initPullview();
        initView(mParent);
        initData();
        updateView();
    }
    protected void initPullview(){
        pullview=findViewByID_My(R.id.pullview);
        if(pullview!=null){
            // 设置pull进度条的样式
            pullview.getHeaderView().setHeaderProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));
            pullview.getFooterView().setFooterProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));
        }
    }

    protected  <T extends View> T findViewByID_My(int id){
        return (T) mParent.findViewById(id);
    }

    protected abstract void initView(View view);
    protected abstract void initData();
    protected abstract void updateView();

    protected void myToast(String content){
        Toast.makeText(this.getActivity(),content,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }
}
