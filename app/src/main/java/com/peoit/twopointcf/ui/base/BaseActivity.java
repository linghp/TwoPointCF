package com.peoit.twopointcf.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.base.IBaseView_Response;
import com.peoit.twopointcf.ui.view.LoadingLayout;
import com.peoit.twopointcf.ui.view.TitleView;
import com.peoit.twopointcf.ui.view.pullview.AbPullToRefreshView;
import com.peoit.twopointcf.utils.LocalUserInfo;

import java.util.HashMap;

/**
 * Created by ling on 2015/8/6.
 * last:2015/8/6
 * description:
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView_Response,View.OnClickListener{
    private ProgressDialog pd;
    private ViewGroup layout_body;
    protected TitleView titleView;
    protected AbPullToRefreshView pullview;
    protected View layout_current;
    protected LocalUserInfo localUserInfo;
    protected LoadingLayout loadingLayout;
    protected LayoutInflater inflater;
    public HashMap<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BaseActivity", getClass().getSimpleName());
        localUserInfo = LocalUserInfo.getInstance(this);
        inflater=LayoutInflater.from(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.layout_base);
        initContentView(layoutResID);
        initCommonView();
        initView();
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

        loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
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

    protected void initContentView(int layoutResID) {
        titleView = (TitleView) super.findViewById(R.id.title_view);

        layout_body = (FrameLayout) super.findViewById(R.id.layout_body);

        layout_current = getLayoutInflater().inflate(layoutResID, layout_body);

        //layout_body.addView(layout_current);
    }

    /**
     * 无数据或出错时点击重新加载时调用
     */
    public void requestServer(){

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void updateView();

    protected void myToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected  <T extends View> T findViewByID_My(int id){
        return (T) super.findViewById(id);
    }

    /**
     * 在fragment中请求服务器或本地判断，成功后在activity中做进一步操作
     */
    public void onResultSuccess(){

    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (pd == null) {
            pd = new ProgressDialog(this);
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
        if (!isFinishing()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    // 触摸其他区域，输入法界面消失
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
