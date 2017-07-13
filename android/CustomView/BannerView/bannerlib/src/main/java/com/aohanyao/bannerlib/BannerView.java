package com.aohanyao.bannerlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aohanyao.bannerlib.adapter.BannerAdapter;
import com.aohanyao.bannerlib.inf.BannerClickListener;
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
    private int mRadiusIndicatorSelector;//圆点的样式
    private int mRadiusIndicatorGravity;//圆点的方向
    private int mLoopDuration;//播放的速度

    public void setBannerClickListener(BannerClickListener bannerClickListener) {
        if (mAdapter != null) {
            mAdapter.setBannerClickListener(bannerClickListener);
        }
    }

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initUi();
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerView);
        mLoopDuration = typedArray.getInteger(R.styleable.BannerView_loop_duration, 3000);
        mRadiusIndicatorSelector = typedArray.getResourceId(R.styleable.BannerView_radius_indicator_selector,R.drawable.page_indicator_selector);
        mRadiusIndicatorGravity = typedArray.getInt(R.styleable.BannerView_radius_indicator_gravity, 17);
        typedArray.recycle();
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
            view.setBackgroundResource(mRadiusIndicatorSelector);
            mRadiusIndicatorView.add(view);
            mIndicatorLayout.addView(view);
        }


        //创建适配器  创建监听 start
        mAdapter = new BannerAdapter(mViewPager, mDatas, mBannerHelper, getContext());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setBannerListener(new BannerViewPager.BannerListener() {
            @Override
            public void onBannerSelector(int position) {
                //初始化 创建 圆点 end
                resetRadiusIndicator(position);
            }
        });

        //创建适配器  创建监听 end
        mViewPager.setCurrentItem(1);
        //  mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setScrollDurationFactor(5);
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
            if (positon != 0) {
                positon--;
            }
            if (positon == mAdapter.getCount() - 3) {
                positon = mDatas.size() - 3;
            }
            if (positon == mAdapter.getCount() - 2) {
                positon = 0;
            }
            mRadiusIndicatorView.get(positon).setSelected(true);
        }
    }

    /**
     * 初始化
     */
    private void initUi() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_banner_view, this, true);
        mViewPager = (BannerViewPager) findViewById(R.id.ncvp);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.ll_radius_index);
        mIndicatorLayout.setGravity(mRadiusIndicatorGravity);
    }

    /**
     * 开始自动循环
     */
    public void startBanner() {
        mViewPager.startBanner(mLoopDuration);
    }

    /**
     * 停止自动循环
     */
    public void stopBanner() {
        mViewPager.stopBanner(true);
    }

    public void setmRadiusIndicatorSelector(int mRadiusIndicatorSelector) {
        this.mRadiusIndicatorSelector = mRadiusIndicatorSelector;
    }

    public void setmRadiusIndicatorGravity(int mRadiusIndicatorGravity) {
        this.mRadiusIndicatorGravity = mRadiusIndicatorGravity;
        mIndicatorLayout.setGravity(mRadiusIndicatorGravity);
        mIndicatorLayout.postInvalidate();
    }

    public void setmLoopDuration(int mLoopDuration) {
        this.mLoopDuration = mLoopDuration;
    }
}
