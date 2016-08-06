package com.aohanyao.scroll.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * <p>作者：江俊超 on 2016/8/1 15:59</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p></p>
 */
public class OffsetScrollView extends LinearLayout {
    public OffsetScrollView(Context context) {
        this(context, null);
    }

    public OffsetScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OffsetScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int lastX, lastY;

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
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                lastX = rawX;
                lastY = rawY;
                break;
        }
        return true;
    }
}
