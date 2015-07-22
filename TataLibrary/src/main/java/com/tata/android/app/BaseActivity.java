package com.tata.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.tata.android.utils.IntentUtils;
import com.tata.android.utils.ToastUtils;

public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    
    public String TAG = this.getClass().getSimpleName();
    
    /**
     * look view from the whole window 's decor view for the given id.
     * 
     * @param resId
     * @return
     * @Author Terry Liu
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T findView(int resId) {
        View view = this.getWindow().findViewById(resId);
        if (view == null)
            throw new NullPointerException("can't find any view associated with the resId");
        return (T) view;
    }
    
    /**
     * Register a callback to be invoked for these views .
     * 
     * @param views
     * @Author Terry Liu
     */
    public void setListener(View... views) {
        if (views == null)
            throw new IllegalArgumentException("the given views shouldn't be null ");
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(this);
            }
        }
    }
    
    /**
     * called when the view is associated with activity
     * 
     * @Author Terry Liu
     */
    public void initView() {
    }
    
    /**
     * initialize data when the view is binded successfully
     * 
     * @Author Terry Liu
     */
    public void initData() {
    }
    
    @Override
    public void onClick(View v) {
        
    }
    
    /**
     * retrieve a new Intent
     * 
     * @return Intent
     */
    public Intent getNewIntent() {
        return new Intent();
    }
    
    /**
     * retrieve a context of current Activity
     * 
     * @return Activity
     */
    public Activity getActivity() {
        return this;
    }
    
    public void show(String s) {
        ToastUtils.showToast(this, s);
    }
    
    public void show(int resId) {
        ToastUtils.showToast(this, resId);
    }
    
    public void jumpToActivity(Class<? extends Activity> clz) {
        IntentUtils.jumpToActivity(this, clz);
    }

    public void jumpWithAction(Class<? extends Activity> clz, String action) {
        IntentUtils.jumpToActivity(this, clz, action);
    }
    
    public void jumpWithBundle(Class<? extends Activity> clz, Bundle data) {
        IntentUtils.jumpToActivityWithData(this, clz, data);
    }
}
