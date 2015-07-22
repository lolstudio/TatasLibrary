package com.tata.android.ui.base;

import android.app.Activity;
import android.content.Context;

import com.tata.android.adapter.base.BaseViewHolder;
import com.tata.android.adapter.base.ViewInjectorByReflect;

public abstract class AbstractViewHolder implements BaseViewHolder {
    
    
    public static void init(Context context){
        
    }
    public static void injectView(Context context) {
//        ViewInjectorByReflect.injectView(this, ((Activity) context).getWindow().getDecorView());
    }
}
