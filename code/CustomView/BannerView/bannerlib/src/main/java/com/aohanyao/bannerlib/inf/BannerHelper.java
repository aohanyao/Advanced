package com.aohanyao.bannerlib.inf;

import android.content.Context;
import android.view.View;

/**
 * <p>作者：江俊超 on 2016/8/24 09:57</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>BannerHelper 提供外接口进行访问</p>
 */
public interface BannerHelper<T> {
    View getView(Context  mContext,T data);
}
