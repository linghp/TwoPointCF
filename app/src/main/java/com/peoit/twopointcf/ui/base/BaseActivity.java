package com.peoit.twopointcf.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.view.TitleView;

/**
 * Created by ling on 2015/8/6.
 * last:2015/8/6
 * description:
 */
public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout layout_body;
    protected TitleView titleView;
    protected View layout_current;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.layout_base);
        initContentView(layoutResID);
        initView();
        initData();
        updateView();
    }

    protected void initContentView(int layoutResID) {
        titleView = (TitleView) super.findViewById(R.id.title_view);

        layout_body = (FrameLayout) super.findViewById(R.id.layout_body);

        layout_current = getLayoutInflater().inflate(layoutResID, null);

        layout_body.addView(layout_current);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void updateView();

    protected void myToast(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
