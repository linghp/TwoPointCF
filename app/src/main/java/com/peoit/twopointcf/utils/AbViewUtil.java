/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peoit.twopointcf.utils;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;


// TODO: Auto-generated Javadoc
/**
 * © 2012 amsoft.cn
 * 名称：AbViewUtil.java 
 * 描述：View工具类.
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-01-17 下午11:52:13
 */

public class AbViewUtil {
    
    /**
     * 无效值
     */
    public static final int INVALID = Integer.MIN_VALUE;
    
	/**
	 * 描述：重置AbsListView的高度. item 的最外层布局要用
	 * RelativeLayout,如果计算的不准，就为RelativeLayout指定一个高度
	 * 
	 * @param absListView
	 *            the abs list view
	 * @param lineNumber
	 *            每行几个 ListView一行一个item
	 * @param verticalSpace
	 *            the vertical space
	 */
	public static void setAbsListViewHeight(AbsListView absListView,
			int lineNumber, int verticalSpace) {

		int totalHeight = getAbsListViewHeight(absListView, lineNumber,
				verticalSpace);
		ViewGroup.LayoutParams params = absListView.getLayoutParams();
		params.height = totalHeight;
		((MarginLayoutParams) params).setMargins(0, 0, 0, 0);
		absListView.setLayoutParams(params);
	}

	/**
	 * 描述：获取AbsListView的高度.
	 *
	 * @param absListView            the abs list view
	 * @param lineNumber            每行几个 ListView一行一个item
	 * @param verticalSpace            the vertical space
	 * @return the abs list view height
	 */
	public static int getAbsListViewHeight(AbsListView absListView,
			int lineNumber, int verticalSpace) {
		int totalHeight = 0;
		int w = MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED);
		int h = MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED);
		absListView.measure(w, h);
		ListAdapter mListAdapter = absListView.getAdapter();
		if (mListAdapter == null) {
			return totalHeight;
		}

		int count = mListAdapter.getCount();
		if (absListView instanceof ListView) {
			for (int i = 0; i < count; i++) {
				View listItem = mListAdapter.getView(i, null, absListView);
				listItem.measure(w, h);
				totalHeight += listItem.getMeasuredHeight();
			}
			if (count == 0) {
				totalHeight = verticalSpace;
			} else {
				totalHeight = totalHeight
						+ (((ListView) absListView).getDividerHeight() * (count - 1));
			}

		} else if (absListView instanceof GridView) {
			int remain = count % lineNumber;
			if (remain > 0) {
				remain = 1;
			}
			if (mListAdapter.getCount() == 0) {
				totalHeight = verticalSpace;
			} else {
				View listItem = mListAdapter.getView(0, null, absListView);
				listItem.measure(w, h);
				int line = count / lineNumber + remain;
				totalHeight = line * listItem.getMeasuredHeight() + (line - 1)
						* verticalSpace;
			}

		}
		return totalHeight;

	}

	/**
	 * 测量这个view
	 * 最后通过getMeasuredWidth()获取宽度和高度.
	 * @param view 要测量的view
	 * @return 测量过的view
	 */
	public static void measureView(View view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		view.measure(childWidthSpec, childHeightSpec);
	}
	
	/**
	 * 获得这个View的宽度
	 * 测量这个view，最后通过getMeasuredWidth()获取宽度.
	 * @param view 要测量的view
	 * @return 测量过的view的宽度
	 */
	public static int getViewWidth(View view) {
		measureView(view);
		return view.getMeasuredWidth();
	}
	
	/**
	 * 获得这个View的高度
	 * 测量这个view，最后通过getMeasuredHeight()获取高度.
	 * @param view 要测量的view
	 * @return 测量过的view的高度
	 */
	public static int getViewHeight(View view) {
		measureView(view);
		return view.getMeasuredHeight();
	}
	
	/**
	 * 从父亲布局中移除自己
	 * @param v
	 */
	public static void removeSelfFromParent(View v) {
		ViewParent parent = v.getParent();
		if(parent != null){
			if(parent instanceof ViewGroup){
				((ViewGroup)parent).removeView(v);
			}
		}
	}



	/**
	 * 设置PX padding.
	 *
	 * @param view the view
	 * @param left the left padding in pixels
     * @param top the top padding in pixels
     * @param right the right padding in pixels
     * @param bottom the bottom padding in pixels
	 */
	public static void setPadding(View view, int left,
			int top, int right, int bottom) {
		int scaledLeft = CommonUtil.px2dip(view.getContext(), left);
		int scaledTop = CommonUtil.px2dip(view.getContext(), top);
		int scaledRight = CommonUtil.px2dip(view.getContext(), right);
		int scaledBottom = CommonUtil.px2dip(view.getContext(), bottom);
		view.setPadding(scaledLeft, scaledTop, scaledRight, scaledBottom);
	}

	/**
	 * 设置 PX margin.
	 * 
	 * @param view the view
	 * @param left the left margin in pixels
	 * @param top the top margin in pixels
	 * @param right the right margin in pixels
	 * @param bottom the bottom margin in pixels
	 */
	public static void setMargin(View view, int left, int top,
			int right, int bottom) {
		int scaledLeft = CommonUtil.px2dip(view.getContext(), left);
		int scaledTop = CommonUtil.px2dip(view.getContext(), top);
		int scaledRight = CommonUtil.px2dip(view.getContext(), right);
		int scaledBottom = CommonUtil.px2dip(view.getContext(), bottom);
		
		if(view.getLayoutParams() instanceof MarginLayoutParams){
            MarginLayoutParams mMarginLayoutParams = (MarginLayoutParams) view
                    .getLayoutParams();
            if (mMarginLayoutParams != null){
                if (left != INVALID) {
                    mMarginLayoutParams.leftMargin = scaledLeft;
                }
                if (right != INVALID) {
                    mMarginLayoutParams.rightMargin = scaledRight;
                }
                if (top != INVALID) {
                    mMarginLayoutParams.topMargin = scaledTop;
                }
                if (bottom != INVALID) {
                    mMarginLayoutParams.bottomMargin = scaledBottom;
                }
                view.setLayoutParams(mMarginLayoutParams);
            }
        }
		
	}

}
