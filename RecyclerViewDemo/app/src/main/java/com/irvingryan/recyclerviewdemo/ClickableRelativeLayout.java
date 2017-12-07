package com.irvingryan.recyclerviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by yanwentao on 2017/8/24.
 */

public class ClickableRelativeLayout extends RelativeLayout {
    public ClickableRelativeLayout(Context context) {
        super(context);
    }

    public ClickableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClickableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
