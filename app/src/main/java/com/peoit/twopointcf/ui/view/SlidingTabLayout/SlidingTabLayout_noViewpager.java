/*
 * Copyright 2014 Chris Banes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.peoit.twopointcf.ui.view.SlidingTabLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.twopointcf.R;
import com.peoit.twopointcf.utils.MyLogger;


/**
 */
public class SlidingTabLayout_noViewpager extends HorizontalScrollView {

    public interface TabListener {

        void onTabSelected(int pos);

        void onTabReSelected(int pos);

    }

    private static final int TITLE_OFFSET_DIPS = 24;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    //private static final int TAB_VIEW_TEXT_SIZE_SP = 16;

    private int mTitleOffset;

    private int mTabViewLayoutId;
    private int mTabViewTextViewId;

    private int mTabViewTextAppearance;

    private TabListener mTabListener;

    private final SlidingTabStrip mTabStrip;

    private int currentItemPosition=0;

    public SlidingTabLayout_noViewpager(Context context) {
        this(context, null);
    }

    public SlidingTabLayout_noViewpager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout_noViewpager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Disable the Scroll Bar
        setHorizontalScrollBarEnabled(false);
        // Make sure that the Tab Strips fills this View
        setFillViewport(true);

        mTitleOffset = (int) (TITLE_OFFSET_DIPS * getResources().getDisplayMetrics().density);

        mTabStrip = new SlidingTabStrip(context);
        addView(mTabStrip, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingTabLayout);

        if (a.hasValue(R.styleable.SlidingTabLayout_indicatorHeight)) {
            mTabStrip.setSelectedIndicatorHeight(
                    a.getDimensionPixelSize(R.styleable.SlidingTabLayout_indicatorHeight, 0));
        }

        if (a.hasValue(R.styleable.SlidingTabLayout_selectedColor)) {
            mTabStrip.setSelectedIndicatorColor(
                    a.getColor(R.styleable.SlidingTabLayout_selectedColor, 0));
        }

        mTabViewTextAppearance = a.getResourceId(
                R.styleable.SlidingTabLayout_android_textAppearance, 0);

        mTitleOffset = a.getDimensionPixelSize(R.styleable.SlidingTabLayout_contentInsetStart,
                mTitleOffset);

        mTabStrip.setPadding(mTitleOffset, 0, 0, 0);

        a.recycle();
    }

    /**
     * Set the custom layout to be inflated for the tab views.
     *
     * @param layoutResId Layout id to be inflated
     * @param textViewId id of the {@link TextView} in the inflated view
     */
    public void setCustomTabView(int layoutResId, int textViewId) {
        mTabViewLayoutId = layoutResId;
        mTabViewTextViewId = textViewId;
    }

    public void setTabListener(TabListener tabListener) {
        mTabListener = tabListener;
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void init(String[] titles) {
        mTabStrip.removeAllViews();
        populateTabStrip(titles);
    }


    /**
     * Create a default view to be used for tabs. This is called if a custom tab view is not set via
     * {@link #setCustomTabView(int, int)}.
     */
    protected TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        //textView.setTextSize(TAB_VIEW_TEXT_SIZE_SP);
        textView.setTypeface(Typeface.DEFAULT_BOLD);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // If we're running on Honeycomb or newer, then we can use the Theme's
            // selectableItemBackground to ensure that the View has a pressed state
            TypedValue outValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                    outValue, true);
            textView.setBackgroundResource(outValue.resourceId);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // If we're running on ICS or newer, enable all-caps to match the Action Bar tab style
            textView.setAllCaps(true);
        }

        int padding = (int) (TAB_VIEW_PADDING_DIPS * getResources().getDisplayMetrics().density);
        textView.setPadding(padding/2, 0, padding/2, 0);
        //textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        return textView;
    }

    private void populateTabStrip(String[] titles) {
        final OnClickListener tabClickListener = new TabClickListener();

        for (int i = 0; i < titles.length; i++) {
            View tabView = null;
            TextView tabTitleView = null;

            if (mTabViewLayoutId != 0) {
                // If there is a custom tab view layout id set, try and inflate it
                tabView = LayoutInflater.from(getContext()).inflate(mTabViewLayoutId, mTabStrip,
                        false);
                tabTitleView = (TextView) tabView.findViewById(mTabViewTextViewId);
            }

            if (tabView == null) {
                tabView = createDefaultTabView(getContext());
            }

            if (tabTitleView == null && TextView.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView;
            }

            if (mTabViewTextAppearance != 0) {
                tabTitleView.setTextAppearance(getContext(), mTabViewTextAppearance);
            }

            tabTitleView.setText(titles[i]);
            tabView.setOnClickListener(tabClickListener);
            mTabStrip.addView(tabView);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

            scrollToTab(currentItemPosition, 0);
    }

    private void scrollToTab(int tabIndex, float positionOffset) {
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex > tabStripChildCount) {
            return;
        }

        setSelectedTab(tabIndex, positionOffset);

        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (selectedChild != null) {
            int targetScrollX = selectedChild.getLeft() +
                    Math.round(selectedChild.getWidth()) + 480;
            MyLogger.i(targetScrollX+"");
            scrollTo(targetScrollX, 0);
        }
    }

    private void setSelectedTab(int position, float positionOffset) {
        for (int i = 0; i < mTabStrip.getChildCount(); i++) {
            mTabStrip.getChildAt(i).setActivated(position == i);
        }
        mTabStrip.onViewPagerPageChanged(position, positionOffset);
    }


    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                if (v == mTabStrip.getChildAt(i)) {
                    scrollToTab(i, 0);
                    if (mTabListener != null) {
                        if (currentItemPosition != i) {
                            mTabListener.onTabSelected(i);
                        } else {
                            mTabListener.onTabReSelected(i);
                        }
                    }
                    currentItemPosition=i;
                    return;
                }
            }
        }
    }

}
