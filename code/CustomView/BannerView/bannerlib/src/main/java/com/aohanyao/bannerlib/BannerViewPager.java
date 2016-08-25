package com.aohanyao.bannerlib;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import com.aohanyao.bannerlib.tran.ScrollerCustomDuration;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>作者：江俊超 on 2016年8月24日09:13:28</p>
 * <p>邮箱：928692385@qq.com</p>
 */
public class BannerViewPager extends ViewPager {
    private BannerListener bannerListener;
    private String TAG = "BannerViewPager";
    private OnPageChangeListener onPageChangeListener;
    private int count;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private long mBannerSpeed = 3500;
    private ScrollerCustomDuration mScroller = null;
    private boolean isStop = false;

    public BannerViewPager(Context context) {
        super(context);
        postInitViewPager();
    }


    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    public void setBannerListener(BannerListener bannerListener) {
        this.bannerListener = bannerListener;
    }

    @Override
    public void setAdapter(final PagerAdapter adapter) {
        count = adapter.getCount();
        super.setAdapter(adapter);
        //滑动监听 start
        onPageChangeListener = new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setScrollDurationFactor(5);
                if (bannerListener != null) {
                    bannerListener.onBannerSelector(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
        //滑动监听 end

        //开始监听事件
        addOnPageChangeListener(onPageChangeListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                setScrollDurationFactor(0);
//                stopBanner(isStop);
                break;
            case MotionEvent.ACTION_UP:
//                if (!isStop)
//                    startBanner(mBannerSpeed);
                break;
            case MotionEvent.ACTION_MOVE:
                stopBanner(isStop);
                break;
        }
        return super.onTouchEvent(ev);
    }

    public interface BannerListener {
        void onBannerSelector(int position);
    }

    /**
     * 开始自动循环
     */
    public void startBanner(long speed) {
        if (speed != 0) {
            this.mBannerSpeed = speed;
        }

        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = handler.obtainMessage();
                handler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask, mBannerSpeed, mBannerSpeed);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentItem = getCurrentItem();
            //开始滚动
            if (currentItem == count - 1) {
                setCurrentItem(0, false);
            } else {
                currentItem++;
                setCurrentItem(currentItem, true);

            }
            super.handleMessage(msg);
        }
    };


    /**
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private void postInitViewPager() {
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ScrollerCustomDuration(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
        }
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
       // mScroller.setScrollDurationFactor(scrollFactor);
    }

    /**
     * 停止自动循环
     */
    public void stopBanner(boolean isStop) {
        this.isStop = isStop;
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }
}
