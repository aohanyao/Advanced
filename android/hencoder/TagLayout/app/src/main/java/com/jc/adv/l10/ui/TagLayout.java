package com.jc.adv.l10.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 江俊超 on 2018/12/3.
 * Version:1.0
 * Description:
 * ChangeLog:
 * ----------------------------------------------------
 * 1. 测量
 * ----------------------------------------------------
 */
public class TagLayout extends ViewGroup {
    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 1. 测量高度，测量宽度
        // 2. 同一行中高度最高的是高度，宽度不能大于View的宽度
        // 总高度
        float sumHeight = 0;
        // 行高度
        float lineHeight = 0;
        // 已经使用的宽度
        int widthUse= getWidth();
        // 遍历所有的子View
        for (int i = 0; i < getChildCount(); i++) {
            // 获取子View
            View childView = getChildAt(i);

            // 测量子view
//            measureChildWithMargins(childView,widthMeasureSpec,widthUse//);

        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
