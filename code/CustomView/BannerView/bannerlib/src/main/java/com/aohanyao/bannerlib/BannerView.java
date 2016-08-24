package com.aohanyao.bannerlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aohanyao.bannerlib.adapter.BannerAdapter;
import com.aohanyao.bannerlib.inf.BannerHelper;
import com.aohanyao.bannerlib.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import aohanyao.com.bannerlib.R;

/**
 * <p>作者：江俊超 on 2016/8/23 15:45</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>轮播图控件</p>
 */
public class BannerView<T> extends RelativeLayout {

    private BannerViewPager mViewPager;
    private LinearLayout mIndicatorLayout;
    private List<T> mDatas;
    private BannerHelper mBannerHelper;
    //小圆点
    private List<View> mRadiusIndicatorView;
    private BannerAdapter mAdapter;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 设置数据和helper
     */
    public void setData(List<T> datas, BannerHelper<T> bannerHelper) {
        this.mDatas = datas;
        this.mBannerHelper = bannerHelper;

        updataUi();
    }

    /**
     * 初始化UI
     */
    private void updataUi() {
        //初始化 创建 圆点 start
        int margin = DensityUtils.dp2px(getContext(), 3f);
        int indicatorWidth = DensityUtils.dp2px(getContext(), 8);
        mRadiusIndicatorView = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            View view = new View(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(indicatorWidth, indicatorWidth);
            layoutParams.setMargins(margin, margin, margin, margin);
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.drawable.page_indicator_selector);
            mRadiusIndicatorView.add(view);
            mIndicatorLayout.addView(view);
        }
        //初始化 创建 圆点 end
        resetRadiusIndicator(0);

        //创建适配器  创建监听 start
        mAdapter = new BannerAdapter(mDatas, mBannerHelper, getContext());
        mAdapter.setmViewPager(mViewPager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setBannerListener(new BannerViewPager.BannerListener() {
            @Override
            public void onBannerSelector(int position) {
                //初始化 创建 圆点 end
                resetRadiusIndicator(position);
            }
        });
        //创建适配器  创建监听 end

    }

    /**
     * 重置圆点
     *
     * @param positon
     */
    private void resetRadiusIndicator(int positon) {
        if (mRadiusIndicatorView != null) {
            for (View view : mRadiusIndicatorView) {
                view.setSelected(false);
            }
            mRadiusIndicatorView.get(positon).setSelected(true);
        }
    }

    /**
     * 初始化
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_banner_view, this, true);
        mViewPager = (BannerViewPager) findViewById(R.id.ncvp);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.ll_radius_index);
    }

    /**
     * 开始自动循环
     */
    public void startBanner(){
        mViewPager.startBanner(0);
    }

    /**
     * 停止自动循环
     */
    public void stopBanner(){
        mViewPager.stopBanner();
    }
}
