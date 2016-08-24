package com.aohanyao.bannerlib.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.aohanyao.bannerlib.BannerViewPager;
import com.aohanyao.bannerlib.inf.BannerHelper;

import java.util.List;

/**
 * <p>作者：江俊超 on 2016/8/24 10:22</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class BannerAdapter<T> extends PagerAdapter {
    private List<T> mDatas;
    private BannerHelper mBannerHelper;
    private Context mContext;
    private BannerViewPager mViewPager;

    public void setmViewPager(BannerViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }

    public BannerAdapter(List<T> mDatas, BannerHelper mBannerHelper, Context mContext) {
        this.mDatas = mDatas;
        this.mBannerHelper = mBannerHelper;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mBannerHelper.getView(mContext, mDatas.get(position));
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
