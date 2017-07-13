package aohanyao.com.settingitemview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * <p>作者：江俊超 on 2016/8/9 15:08</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>组合控件：设置的item</p>
 */
public class SettingItemView extends FrameLayout {
    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    /**
     * 获取属性
     * @param attrs
     */
    private void initAttr(AttributeSet attrs) {

    }
}
