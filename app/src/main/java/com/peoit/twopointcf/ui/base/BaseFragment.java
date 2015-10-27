package com.peoit.twopointcf.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.ui.activity.MainActivity;
import com.peoit.twopointcf.ui.view.LoadingLayout;
import com.peoit.twopointcf.ui.view.TitleView;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;
import com.peoit.twopointcf.utils.LocalUserInfo;
import com.peoit.twopointcf.utils.MyLogger;

/**
 * Created by ling on 2015/7/31.
 * last:2015/7/31
 * description:
 */
public abstract class BaseFragment extends Fragment implements IBaseView_Response,View.OnClickListener{
    private ProgressDialog pd;
    protected View mParent;
    protected BaseActivity mActivity;
    protected TitleView titleView;
    protected AbPullToRefreshView pullview;
    protected LoadingLayout loadingLayout;
    protected LocalUserInfo localUserInfo;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("BaseFragment", getClass().getSimpleName());
        mParent = getView();
        if(!(getActivity() instanceof MainActivity)){
            mActivity = (BaseActivity) getActivity();
        }
        titleView= (TitleView) getView().findViewById(R.id.title_view);
        localUserInfo = LocalUserInfo.getInstance(getActivity());
        initCommonView();
        initView(mParent);
        initData();
        updateView();
    }
    protected void initCommonView(){
        pullview=findViewByID_My(R.id.pullview);
        if(pullview!=null){
            // 设置pull进度条的样式
            pullview.getHeaderView().setHeaderProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));
            pullview.getFooterView().setFooterProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));
        }

        loadingLayout = findViewByID_My(R.id.loading_layout);
        if(loadingLayout!=null) {
            loadingLayout.setOnRetryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadingLayout.showLoading();
                    requestServer();
                }
            });
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

    public void requestServer(){

    }

    @Override
    public void showProgress(boolean flag, String message) {
        MyLogger.i("showProgress");
        if (pd == null) {
            pd = new ProgressDialog(getActivity());
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        } else {
            pd.show();
        }
    }

    @Override
    public void hideProgress() {
        if (pd == null)
            return;

        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        if (!getActivity().isFinishing()) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getStringbyid(int resId) {
        return getString(resId);
    }


    @Override
    public void showLoadingPage() {
        loadingLayout.showLoading();
    }

    @Override
    public void showErrorPage() {
        loadingLayout.showError();
    }

    @Override
    public void showEmptyPage() {
        loadingLayout.showEmpty();
    }

    @Override
    public void showContentPage() {
        loadingLayout.showContent();
    }

    @Override
    public void onClick(View v) {

    }

}
