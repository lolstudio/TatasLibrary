package com.tata.android.fragment.utils;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;

/**
 * protected void initTabManager() { mTabManager = new TabManager(this,
 * mTabHost, R.id.real_tab_content);
 * mTabManager.addTab(mTabHost.newTabSpec("tab_feature_posts"
 * ).setIndicator("One"), FeaturedPostsFragment.class, null);
 * mTabManager.addTab(mTabHost.newTabSpec("tab_apply").setIndicator("Two"),
 * NearFragment.class, null);
 * mTabManager.addTab(mTabHost.newTabSpec("tab_personalmsg"
 * ).setIndicator("Three"), PrivateMessageListFragment.class, null);
 * mTabManager.addTab(mTabHost.newTabSpec("tab_more").setIndicator("Four"),
 * MoreFragment.class, null);
 * 
 * }
 * 
 *
 * @Description :
 *
 * @Author : Terry Liu
 *
 * @ModifiedTime : 2014年8月25日 下午2:13:30
 *
 * @Version : 2.7.0
 *
 */
public class TabManager implements TabHost.OnTabChangeListener {
    
    private final FragmentActivity mActivity;
    private final TabHost mTabHost;
    private final int mContainerId;
    private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
    TabInfo mLastTab;
    
    static final class TabInfo {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;
        private Fragment fragment;
        
        TabInfo(String _tag, Class<?> _class, Bundle _args) {
            tag = _tag;
            clss = _class;
            args = _args;
        }
    }
    
    // 如何使用
    protected void initTabManager() {
        // mTabManager = new TabManager(this, mTabHost, R.id.real_tab_content);
        // mTabManager.addTab(mTabHost.newTabSpec("tab_feature_posts").setIndicator("One"),
        // FeaturedPostsFragment.class,
        // null);
        // mTabManager.addTab(mTabHost.newTabSpec("tab_apply").setIndicator("Two"),
        // NearFragment.class, null);
        // mTabManager.addTab(mTabHost.newTabSpec("tab_personalmsg").setIndicator("Three"),
        // PrivateMessageListFragment.class, null);
        // mTabManager.addTab(mTabHost.newTabSpec("tab_more").setIndicator("Four"),
        // MoreFragment.class, null);
        
    }
    
    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;
        
        public DummyTabFactory(Context context) {
            mContext = context;
        }
        
        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }
    
    public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
        mActivity = activity;
        mTabHost = tabHost;
        mContainerId = containerId;
        mTabHost.setOnTabChangedListener(this);
    }
    
    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
        tabSpec.setContent(new DummyTabFactory(mActivity));
        String tag = tabSpec.getTag();
        
        TabInfo info = new TabInfo(tag, clss, args);
        
        // Check to see if we already have a fragment for this tab, probably
        // from a previously saved state. If so, deactivate it, because our
        // initial state is that a tab isn't shown.
        info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
        if (info.fragment != null && !info.fragment.isDetached()) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            ft.detach(info.fragment);
            ft.commitAllowingStateLoss();
        }
        
        mTabs.put(tag, info);
        mTabHost.addTab(tabSpec);
    }
    
    public Fragment getLastTabFragment() {
        return mLastTab.fragment;
    }
    
    @Override
    public void onTabChanged(String tabId) {
        TabInfo newTab = mTabs.get(tabId);
        if (newTab != null) {
            if (newTab.tag.equals("tab_feature_posts")) {
                // MobclickAgent.onEvent(mActivity, "主屏按钮", "精选");
            } else if (newTab.tag.equals("tab_new_posts")) {
                // MobclickAgent.onEvent(mActivity, "主屏按钮", "新帖");
            } else if (newTab.tag.equals("tab_personalmsg")) {
                // MobclickAgent.onEvent(mActivity, "主屏按钮", "私信");
            } else if (newTab.tag.equals("tab_more")) {
                // MobclickAgent.onEvent(mActivity, "主屏按钮", "更多");
            } else if (newTab.tag.equals("tab_apply")) {
                // MobclickAgent.onEvent(mActivity, "主屏按钮", "附近");
            }
            
        }
        
        if (mLastTab != newTab) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    ft.hide(mLastTab.fragment);
                    // ft.detach(mLastTab.fragment);
                }
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(mActivity, newTab.clss.getName(), newTab.args);
                    ft.add(mContainerId, newTab.fragment, newTab.tag);
                } else {
                    if (newTab.fragment.isHidden())
                        ft.show(newTab.fragment);
                    else
                        ft.attach(newTab.fragment);
                }
            }
            
            mLastTab = newTab;
            ft.commitAllowingStateLoss();
            mActivity.getSupportFragmentManager().executePendingTransactions();
        }
    }
    
}
