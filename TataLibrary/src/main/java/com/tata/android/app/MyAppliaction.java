package com.tata.android.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MyAppliaction extends Application {

    private View view;
    ViewGroup viewGropu;
    private Context context;
    private Activity mActivity;
    private Looper looper;
	private static MyAppliaction mApplication;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}


}
