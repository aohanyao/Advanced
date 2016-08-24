package com.aohanyao.bannerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aohanyao.bannerlib.inf.BannerHelper;
import com.squareup.picasso.Picasso;

/**
 * <p>作者：江俊超 on 2016/8/24 16:12</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class SampleBannerHelper implements BannerHelper<String> {
    @Override
    public View getView(Context mContext, String data) {
        ImageView imageView = new ImageView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(mContext).load(data).into(imageView);
        return imageView;
    }
}
