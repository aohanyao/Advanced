package com.aohanyao.coolmenu.util;

import android.view.View.MeasureSpec;

/**
 * <p>作者：江俊超 on 2016/8/23 17:03</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class MeasureUtil {
    /**
     * 测量宽高 模式
     *
     * @param measureSpec
     * @return
     */
    public static int measureSize(int measureSpec,int defaultSize) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = defaultSize;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
}
