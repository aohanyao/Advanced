package com.aohanyao.slidemenu.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.aohanyao.slidemenu.util.DensityUtils;
import com.aohanyao.slidemenu.util.ScreenUtils;
import com.nineoldandroids.view.ViewHelper;

/**
 * <p>作者：江俊超 on 2016/8/29 17:45</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>侧滑菜单</p>
 */
public class SlideMenu extends HorizontalScrollView {


    private int screenWidth;
    private int contentOffset;
    private int scroolOffset;
    private boolean isFristMeasure = false;
    private int menuWidth;
    private ViewGroup menu;
    private ViewGroup content;

    public SlideMenu(Context context) {
        super(context);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //获取屏幕的宽度
        screenWidth = ScreenUtils.getScreenWidth(getContext());
        contentOffset = DensityUtils.dp2px(getContext(), 80);
        scroolOffset = DensityUtils.dp2px(getContext(), 50);
        menuWidth = screenWidth - contentOffset;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / menuWidth;
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        ViewHelper.setScaleX(menu, leftScale);
        ViewHelper.setScaleY(menu, leftScale);
        ViewHelper.setAlpha(menu, 0.6f + 0.4f * (1 - scale));
        ViewHelper.setTranslationX(menu, menuWidth * scale * 0.6f);

        ViewHelper.setPivotX(content, 0);
        ViewHelper.setPivotY(content, content.getHeight() / 2);
        ViewHelper.setScaleX(content, rightScale);
        ViewHelper.setScaleY(content, rightScale);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isFristMeasure) {
            //获得第一个子元素
            ViewGroup warp = (ViewGroup) getChildAt(0);
            //获得菜单
            menu = (ViewGroup) warp.getChildAt(0);
            //获得内容
            content = (ViewGroup) warp.getChildAt(1);

            menu.getLayoutParams().width = menuWidth;
            content.getLayoutParams().width = screenWidth;
            isFristMeasure = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            isFristMeasure = true;
            this.scrollTo(menuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                if (getScrollX() > scroolOffset) {
                    this.smoothScrollTo(menuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
