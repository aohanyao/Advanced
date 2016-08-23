package aohanyao.com.scolltabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/**
 * <p>作者：江俊超 on 2016/8/22 15:08</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>悬浮的TabView</p>
 */
public class ScrollLevitateTabView extends ScrollView {
    private OnScrollLintener onScrollLintener;
    public void setOnScrollLintener(OnScrollLintener onScrollLintener) {
        this.onScrollLintener = onScrollLintener;
    }
    public ScrollLevitateTabView(Context context) {
        this(context,null);
    }

    public ScrollLevitateTabView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollLevitateTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        //增加视图监听 在整个视图树绘制完成后会回调
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScrollLintener.onScroll(getScrollY());
            }
        });
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollLintener != null) {
            onScrollLintener.onScroll(t);
        }
    }

    public interface OnScrollLintener{
        void onScroll(int scrollY);
    }
}
