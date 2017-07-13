package com.aohanyao.scroll.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * <p>作者：江俊超 on 2016/8/1 15:44</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p></p>
 */
public class LayoutScrollView extends LinearLayout {
    public LayoutScrollView(Context context) {
        this(context, null);
    }

    public LayoutScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int lastX, lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
