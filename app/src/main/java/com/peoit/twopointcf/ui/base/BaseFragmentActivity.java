package com.peoit.twopointcf.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.peoit.twopointcf.R;

/**
 * Created by ling on 2015/8/13.
 * last:2015/8/13
 * description:
 */
public abstract class BaseFragmentActivity extends BaseActivity implements View.OnClickListener{
    protected FragmentManager fragmentManager;

    @Override
    protected void initView() {
        titleView.setBack(this);
    }

    @Override
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
    }

    protected void addFragmentToContainer(Fragment fragment,String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment, tag);
        fragmentTransaction.commit();
    }
    protected void addFragmentToStack(Fragment fragment,String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment, tag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                if (fragmentManager.getBackStackEntryCount() > 0) {
                        fragmentManager.popBackStack();
                } else {
                    finish();
                }
                break;
        }
    }
}
