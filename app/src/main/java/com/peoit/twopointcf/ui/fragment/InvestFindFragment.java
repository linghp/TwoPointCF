package com.peoit.twopointcf.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.ui.base.BaseFragment;
import com.peoit.twopointcf.ui.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ling
 *	投资发现
 */
public class InvestFindFragment extends BaseFragment {
    private ViewPager mViewPager;
    private TabPagerAdapter mAdapter;
    private SlidingTabLayout mSlidingTabStrip;
    private DiscoverTab[] mTabs;
    private boolean isLoggedIn=false;
    private int mCurrentItem=0;

	public static InvestFindFragment newInstance(int index) {
		InvestFindFragment f = new InvestFindFragment();

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
		View view = inflater.inflate(R.layout.fragment_investfind, container,
				false);
        mAdapter = new TabPagerAdapter(getChildFragmentManager());

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        //mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.spacing_minor));

        mSlidingTabStrip = (SlidingTabLayout) view.findViewById(R.id.viewpager_tabs);
        mSlidingTabStrip.setViewPager(mViewPager);
        mSlidingTabStrip.setTabListener(new SlidingTabLayout.TabListener() {
            @Override
            public void onTabSelected(int pos) {
                // NO-OP
            }

            @Override
            public void onTabReSelected(int pos) {
//                final Fragment fragment = mAdapter.getItem(pos);
//                if (fragment instanceof ListFragment) {
//                    ((ListFragment) fragment).smoothScrollTo(0);
//                }
            }
        });
        populateMovieDiscoverUi();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        if(titleView!=null){
            titleView.hideLeftBtn();
            titleView.setTitle(R.string.investfind);
        }
	}

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {

    }


    @Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
		}
	}

    private void populateMovieDiscoverUi() {
        if (isLoggedIn) {
            setTabs(DiscoverTab.POPULAR, DiscoverTab.IN_THEATRES, DiscoverTab.UPCOMING,
                    DiscoverTab.RECOMMENDED,DiscoverTab.RECOMMENDED);
        } else {
            setTabs(DiscoverTab.POPULAR, DiscoverTab.IN_THEATRES, DiscoverTab.UPCOMING);
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
            case POPULAR:
                return new InvestFindSubFragment();
            case IN_THEATRES:
                return new InvestFindSubFragment();
            case UPCOMING:
                return new InvestFindSubFragment();
            case RECOMMENDED:
                return new InvestFindSubFragment();
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
                case POPULAR:
                    return R.string.investfind_sub1;
                case IN_THEATRES:
                    return R.string.investfind_sub2;
                case UPCOMING:
                    return R.string.investfind_sub3;
                case RECOMMENDED:
                    return R.string.investfind_sub4;
            }
            return 0;
        }


    }

    public static enum DiscoverTab {
        POPULAR, IN_THEATRES, UPCOMING, RECOMMENDED
    }
}
