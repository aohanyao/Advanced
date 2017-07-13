package com.aohanyao.bannerlib.inf;

import android.view.View;

/**
 * <p>作者：江俊超 on 2016/8/25 16:25</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>条目点击事件</p>
 */
public interface BannerClickListener {
    /**
     * 条目点击事件
     * @param view
     * @param position
     */
    void onBannerClickListener(View view,int position);
}
