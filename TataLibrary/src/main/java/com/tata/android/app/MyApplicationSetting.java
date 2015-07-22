package com.tata.android.app;

import android.content.Context;

import com.tata.android.utils.SharedPreferenceManager;

public class MyApplicationSetting extends SharedPreferenceManager{
	
    
    private Context context;
	
	public MyApplicationSetting(Context context) {
		this.context = context;
	}

}
