package com.aohanyao.jelly.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 *
 */
public class BouncingJellyLinearLayout extends LinearLayout {

    private int dowX;
    private int dowY;
    private int moveX;
    private int moveY;
    private float bouncingOffset = 2850f;
    private float v;
    private ValueAnimator animator;
    private boolean isTop = true;
    public BouncingJellyLinearLayout(Context context) {
        super(context);
    }

    public BouncingJellyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BouncingJellyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 从顶部开始滑动
     */
    public void bouncingTo() {
        ViewHelper.setPivotX(this, getWidth() / 2);
        ViewHelper.setPivotY(this, 0);
        ViewHelper.setScaleY(this, 1.0f + v);
    }
    /**
     * 从顶部开始滑动
     */
    public void bouncingBottom() {
        ViewHelper.setPivotX(this, getWidth() / 2);
        ViewHelper.setPivotY(this, getHeight());
        ViewHelper.setScaleY(this, 1.0f + v);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dowX = (int) event.getRawX();
                dowY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = (int) event.getRawX();
                moveY = (int) event.getRawY();
                int abs = moveY - dowY;
                v = (Math.abs(abs) / bouncingOffset);
                if (v > 0.3f) {
                    v = 0.3f;
                }
                if (abs > 20 && getScrollY() == 0) {
                    isTop = true;
                    bouncingTo();
                    //  requestDisallowInterceptTouchEvent(true);
                    // return true;
                } else if (abs < 0 && getScrollY() + getHeight() >= computeVerticalScrollRange()) {//滚动到底部
                    isTop = false;
                    bouncingBottom();
                    // return true;
                } else {
                    v = 0;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (v > 0) {
                    back(v, 0);
                    return true;
                }
                break;
        }
//        Log.e(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Log.e(TAG, "onTouchEvent: " );
        return super.onTouchEvent(ev);
    }

    private void back(final float from, final float to) {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
            v = 0;
            bouncingTo();
        }
        animator = ValueAnimator.ofFloat(from, to).setDuration(300);
        animator.setInterpolator(new OvershootInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v = (float) animation.getAnimatedValue();
                if (isTop) {
                    bouncingTo();
                } else {
                    bouncingBottom();
                }
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (from == 0) {
                    back(to, from);
                }
            }
        });
        animator.start();
    }
}
