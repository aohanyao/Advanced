package com.aohanyao.bannerlib.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.aohanyao.bannerlib.inf.BannerClickListener;
import com.aohanyao.bannerlib.inf.BannerHelper;

import java.util.List;

/***
 * BannerAdapter
 *
 * @param <T>
 */
public class BannerAdapter<T> extends PagerAdapter {
    private List<T> mDatas;
    private BannerHelper mBannerHelper;
    private Context mContext;
    private int count;
    private BannerClickListener bannerClickListener;

    public void setBannerClickListener(BannerClickListener bannerClickListener) {
        this.bannerClickListener = bannerClickListener;
    }

    public BannerAdapter(final ViewPager pager, List<T> mDatas, BannerHelper mBannerHelper, Context mContext) {
        super();
        this.mBannerHelper = mBannerHelper;
        this.mContext = mContext;
        int actualNoOfIDs = mDatas.size();
        count = actualNoOfIDs + 2;

        T t = mDatas.get(0);//获取到第一个
        T t1 = mDatas.get(mDatas.size() - 1);//获取到最后一个

        this.mDatas = mDatas;
        this.mDatas.add(mDatas.size(), t);
        this.mDatas.add(0, t1);

        pager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(final int position) {
                final int pageCount = getCount();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (position == 0) {
                            pager.setCurrentItem(pageCount - 2, false);
                        } else if (position == pageCount - 1) {
                            //延时切换，避免闪烁
                            pager.setCurrentItem(1, false);
                        }
                    }
                }, 600);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public int getCount() {
        return count;
    }

    public Object instantiateItem(View container, int position) {
        final View view = mBannerHelper.getView(mContext, mDatas.get(position));
        view.setFocusable(true);
        view.setClickable(true);
        view.setTag(position - 1);
        if (bannerClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bannerClickListener.onBannerClickListener(view, (Integer) view.getTag());

                }
            });
        }
        ((ViewPager) container).addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public void finishUpdate(View container) {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View container) {
    }
}