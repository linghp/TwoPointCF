package com.peoit.twopointcf.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseActivity;
import com.peoit.twopointcf.ui.fragment.BusinessManagerFragment;
import com.peoit.twopointcf.ui.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by ling on 2015/8/31.
 * description:（我的项目->）经营管理
 */
public class BusinessManagerActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TabPagerAdapter mAdapter;
    private SlidingTabLayout mSlidingTabStrip;
    private DiscoverTab[] mTabs;
    private int mCurrentItem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_manager);
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mSlidingTabStrip = (SlidingTabLayout)findViewById(R.id.viewpager_tabs);
    }

    @Override
    protected void initData() {
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        //mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.spacing_minor));

        mSlidingTabStrip.setViewPager(mViewPager);
        setTabs(DiscoverTab.INVESTED,DiscoverTab.PUBLISHED);
    }

    @Override
    protected void updateView() {
        if (titleView != null) {
            titleView.setTitle(R.string.title_activity_business_manager);
        }
    }

    public void setTabs(DiscoverTab... tabs) {
        mTabs = tabs;

        if (mAdapter.getCount() != tabs.length) {
            ArrayList<Fragment> fragments = new ArrayList<>();
            for (int i = 0; i < tabs.length; i++) {
                fragments.add(createFragmentForTab(tabs[i]));
            }
            setFragments(fragments);
        }
    }

    private Fragment createFragmentForTab(DiscoverTab tab) {
        switch (tab) {
            case INVESTED:
                return new BusinessManagerFragment();
            case PUBLISHED:
                return new BusinessManagerFragment();
        }
        return null;
    }

    protected void setFragments(List<Fragment> fragments) {
        mAdapter.setFragments(fragments);
        mSlidingTabStrip.notifyDataSetChanged();
        mViewPager.setCurrentItem(mCurrentItem);
    }

    protected class TabPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragments;

        private TabPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
        }

        void setFragments(List<Fragment> fragments) {
            mFragments.clear();
            mFragments.addAll(fragments);
            notifyDataSetChanged();
        }

        @Override
        public final Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public final int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTabTitle(position);
        }

        protected String getTabTitle(int position) {
            if (mTabs != null) {
                return getString(getStringResId(mTabs[position]));
            }
            return null;
        }

        public int getStringResId(DiscoverTab tab) {
            switch (tab) {
                case INVESTED:
                    return R.string.title_activity_invested_project;
                case PUBLISHED:
                    return R.string.published_project;

            }
            return 0;
        }


    }

    public static enum DiscoverTab {
        INVESTED, PUBLISHED
    }

}
