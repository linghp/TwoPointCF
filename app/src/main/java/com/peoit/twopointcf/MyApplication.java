package com.peoit.twopointcf;

import android.app.Application;
import android.view.LayoutInflater;

import com.peoit.twopointcf.config.Global;
import com.peoit.twopointcf.utils.MyLogger;


public class MyApplication extends Application {

	private static MyApplication mInstance;


	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		Global.mInflater = LayoutInflater.from(this);
		Global.mContext = this;

		MyLogger.i("densityDpi", this.getResources().getDisplayMetrics().densityDpi + "");
	}



	public static MyApplication getInstance() {
		return mInstance;
	}



}
