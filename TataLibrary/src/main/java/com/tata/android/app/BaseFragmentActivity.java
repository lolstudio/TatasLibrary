package com.tata.android.app;

import android.app.Fragment;
import android.app.FragmentTransaction;

public abstract class BaseFragmentActivity extends BaseActivity {
    
    public final void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        ft.add(containerViewId, fragment);
        ft.commit();
    }
    public final void addFragment( int containerViewId,Fragment fragment,String tag) {
        FragmentTransaction ft = getFragmentTransaction();
        ft.add(containerViewId, fragment,tag);
        ft.commit();
    }
    public final void replaceFragment(){}
    
    public final FragmentTransaction getFragmentTransaction() {
        return getFragmentManager().beginTransaction();
    }
}
