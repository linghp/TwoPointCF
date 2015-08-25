package com.peoit.twopointcf.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class Global {
	public static Context mContext;

	public static LayoutInflater mInflater;

	public static LinearLayout.LayoutParams PARAM_MP_WC = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
	public static LinearLayout.LayoutParams PARAM_WC_WC = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
	public static LinearLayout.LayoutParams PARAM_MP_MP = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.MATCH_PARENT);
}