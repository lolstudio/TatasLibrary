package com.tata.android.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tata.android.R;


public class OBNavigationBar extends RelativeLayout implements OnClickListener {
    
    private int mBarHeight = 0;
    
    public OBNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    
    public OBNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    
    public OBNavigationBar(Context context) {
        super(context);
        initView(context);
    }
    
    private void initView(Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        mBarHeight = (int) (48 * scale + 0.5f);
        
        setBackgroundResource(R.drawable.ab_stacked_solid_light_holo);
        
        int width = getContext().getResources().getDisplayMetrics().widthPixels;
        setLayoutParams(new LayoutParams(width, mBarHeight));
        
        this.addView(getTitleView(), NavigationView.LEFT_VIEW.ordinal());
        this.addView(getLeftView(), NavigationView.MIDDLE_VIEW.ordinal());
        this.addView(getRightView(), NavigationView.RIGHT_VIEW.ordinal());
        
    }
    
    public static enum NavigationView {
        LEFT_VIEW, MIDDLE_VIEW, RIGHT_VIEW;
    }
    
    public final TextView getLeftView() {
        TextView tv = (TextView) findViewWithTag(NavigationView.LEFT_VIEW);
        if (tv == null) {
            tv = getBaseTextView();
            tv.setTag(NavigationView.LEFT_VIEW);
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_ab_back_holo_light_am, 0, 0, 0);
            tv.setLayoutParams(getBaseLayoutParams(RelativeLayout.ALIGN_PARENT_LEFT));
            tv.setTextSize(18);
            tv.setOnClickListener(this);
        }
        return tv;
    }
    
    public final void setLeftText(String text) {
        getLeftView().setText(text);
    }
    
    public RelativeLayout.LayoutParams getBaseLayoutParams(int... verbs) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, mBarHeight);
        if (verbs != null) {
            for (int verb : verbs) {
                lp.addRule(verb);
            }
        }
        return lp;
    }
    
    /**
     * get the Right view of Navigation bar
     * 
     * @return the TextView assoicated with the navigation bar
     */
    public final TextView getRightView() {
        TextView tv = (TextView) findViewWithTag(NavigationView.RIGHT_VIEW);
        if (tv == null) {
            tv = getBaseTextView();
            tv.setTag(NavigationView.RIGHT_VIEW);
            RelativeLayout.LayoutParams rlp = getBaseLayoutParams(RelativeLayout.ALIGN_PARENT_RIGHT);
            rlp.rightMargin = (leftRightMargin == 0) ? LEFT_AND_RIGHT_MARGIN : leftRightMargin;
            tv.setLayoutParams(rlp);
            tv.setTextSize(18);
            tv.setOnClickListener(this);
        }
        return tv;
    }
    
    private final int LEFT_AND_RIGHT_MARGIN = (int) (8 * 1.5);
    private int leftRightMargin = 0;
    
    public int getLeftRightMargin() {
        return leftRightMargin;
    }
    
    public void setLeftRightMargin(int leftRightMargin) {
        this.leftRightMargin = leftRightMargin;
        requestLayout();
        invalidate();
    }
    
    /**
     * get the middle view of Navigation bar
     * 
     * @return the TextView assoicated with the navigation bar
     */
    public final TextView getTitleView() {
        TextView tv = (TextView) findViewWithTag(NavigationView.MIDDLE_VIEW);
        if (tv == null) {
            tv = getBaseTextView();
            tv.setTag(NavigationView.MIDDLE_VIEW);
            tv.setLayoutParams(getBaseLayoutParams(RelativeLayout.CENTER_IN_PARENT));
            tv.setTextSize(22);
        }
        return tv;
    }
    
    /**
     * get a base TextView with default text color for white
     * 
     * @return
     */
    private final TextView getBaseTextView() {
        TextView tv = new TextView(getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.parseColor("#ffffff"));
        return tv;
    }
    
    /**
     * Sets the string value of the title in the middle of NavigationBar
     * 
     * @param title
     */
    public final void setMiddleText(String title) {
        getTitleView().setText(title);
    }
    
    /**
     * Sets the string value of the title in the middle of NavigationBar
     * 
     * @param title
     */
    public final void setTitle(CharSequence title) {
        getTitleView().setText(title);
    }
    
    /**
     * Sets the string value of the title in the right of NavigationBar
     * 
     * @param title
     */
    public final void setRightText(CharSequence title) {
        getRightView().setText(title);
    }
    
    /**
     * Sets the string value of the title in the right of NavigationBar
     * 
     * @param title
     */
    public final void setRightText(String title) {
        getRightView().setText(title);
    }
    
    public void setLeftView(View view) {
        removeViewAt(NavigationView.LEFT_VIEW.ordinal());
        if (view != null) {
            addView(view, NavigationView.LEFT_VIEW.ordinal(), getLeftView().getLayoutParams());
            requestLayout();
        }
    }
    
    public void setRightView(View view) {
        removeViewAt(NavigationView.RIGHT_VIEW.ordinal());
        if (view != null) {
            addView(view, NavigationView.RIGHT_VIEW.ordinal(), getRightView().getLayoutParams());
            requestLayout();
        }
    }
    
    private OnLeftViewClickListener onLeftViewClickListener;
    
    public OnLeftViewClickListener getOnLeftViewClickListener() {
        return onLeftViewClickListener;
    }
    
    public void setOnLeftViewClickListener(OnLeftViewClickListener onLeftViewClickListener) {
        this.onLeftViewClickListener = onLeftViewClickListener;
    }
    
    private OnRightViewClickListener onRightViewClickListener;
    
    public OnRightViewClickListener getOnRightViewClickListener() {
        return onRightViewClickListener;
    }
    
    public void setOnRightViewClickListener(OnRightViewClickListener onRightViewClickListener) {
        this.onRightViewClickListener = onRightViewClickListener;
    }
    
    @Override
    public void onClick(View v) {
        NavigationView naviView = (NavigationView) v.getTag();
        switch (naviView) {
            case LEFT_VIEW: {
                if (onLeftViewClickListener == null)
                    // finish the current Activity
                    ((Activity) getContext()).finish();
                else
                    onLeftViewClickListener.onLeftViewClick(v);
            }
            break;
            case RIGHT_VIEW:
            if (onRightViewClickListener != null)
                onRightViewClickListener.onRightViewClick(v);
            break;
            default:
            break;
        
        }
    }
    
    /**
     * Interface definition for a callback to be invoked when the right view has
     * been clicked
     */
    public interface OnRightViewClickListener {
        
        /**
         * Called when the right view of navigation bar has been clicked.
         * 
         * @param v
         */
        void onRightViewClick(View v);
    }
    
    /**
     * Interface definition for a callback to be invoked when the left view has
     * been clicked
     */
    public interface OnLeftViewClickListener {
        /**
         * Called when the left view of navigation bar has been clicked.
         * 
         * @param v
         */
        void onLeftViewClick(View v);
    }
}
