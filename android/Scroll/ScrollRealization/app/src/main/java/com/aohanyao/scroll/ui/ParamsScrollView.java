package com.aohanyao.scroll.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * <p>作者：江俊超 on 2016/8/1 16:04</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p></p>
 */
public class ParamsScrollView extends LinearLayout {
    public ParamsScrollView(Context context) {
        this(context, null);
    }

    public ParamsScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParamsScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int lastX, lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ViewGroup.MarginLayoutParams layoutParamsDown = (MarginLayoutParams) getLayoutParams();
                layoutParamsDown.width = layoutParamsDown.width + 20;
                layoutParamsDown.height = layoutParamsDown.height + 20;
                setLayoutParams(layoutParamsDown);
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                ViewGroup.MarginLayoutParams layoutParamsMove = (MarginLayoutParams) getLayoutParams();
                layoutParamsMove.leftMargin=layoutParamsMove.leftMargin+offsetX;
                layoutParamsMove.topMargin=layoutParamsMove.topMargin+offsetY;
                setLayoutParams(layoutParamsMove);
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                ViewGroup.MarginLayoutParams layoutParamsUP = (MarginLayoutParams) getLayoutParams();
                layoutParamsUP.width = layoutParamsUP.width - 20;
                layoutParamsUP.height = layoutParamsUP.height - 20;
                setLayoutParams(layoutParamsUP);
                break;
        }
        return true;
    }
}
