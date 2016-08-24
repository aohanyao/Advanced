package com.aohanyao.bannerlib;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

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

    public BannerViewPager(Context context) {
        super(context);
    }

    private long mBannerSpeed = 3500;

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
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
                if (position == adapter.getCount() - 1) {
                    if (positionOffsetPixels > .5) {
                        setCurrentItem(0);
                    }
                }
                if (position == 0) {

                }
            }

            @Override
            public void onPageSelected(int position) {
                if (bannerListener != null) {
                    bannerListener.onBannerSelector(position);
                }
                stopBanner();
                startBanner(mBannerSpeed);
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
     * 停止自动循环
     */
    public void stopBanner() {
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
