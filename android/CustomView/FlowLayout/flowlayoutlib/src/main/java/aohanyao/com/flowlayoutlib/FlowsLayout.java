package aohanyao.com.flowlayoutlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>作者：江俊超 on 2016/8/1 14:21</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>流失布局</p>
 */
public class FlowsLayout extends ViewGroup {
    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> mAllLineHeight = new ArrayList<>();

    public FlowsLayout(Context context) {
        this(context, null);
    }

    public FlowsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量宽度及模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //包裹模式下的宽高
        int wrapContentWidth = 0;
        int wrapContentHeight = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        //遍历所有的子view 获取宽高
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            //测量子view
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //获取布局参数
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;
            //判断当前的行宽加上子view的宽度是否大于整个宽度
            if (childWidth + lineWidth > sizeWidth) {
                //获取最大宽度
                wrapContentWidth = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;
                wrapContentHeight += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == getChildCount() - 1) {
                //当子view是最后一个的时候 宽度取最大值，防止出现 一个的情况
                wrapContentWidth = Math.max(lineHeight, childWidth);
                //包裹内容情况下 高度相加
                wrapContentHeight += lineHeight;
            }
        }
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : wrapContentWidth, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : wrapContentHeight);

    }


    /***
     * 获取每个子view 确定位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineView = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

            if (childWidth + lineWidth > width) {
                mAllViews.add(lineView);
                lineView = new ArrayList<>();
                mAllLineHeight.add(lineHeight);
                lineWidth = 0;
            }
            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            lineView.add(childView);
        }

        int left = 0;
        int top = 0;
        //遍历所有的行数
        for (int i = 0; i < mAllViews.size(); i++) {
            lineHeight = mAllLineHeight.get(i);
            List<View> childLineViews = mAllViews.get(i);
            for (View childLineView : childLineViews) {
                MarginLayoutParams mlp = (MarginLayoutParams) childLineView.getLayoutParams();
                if (childLineView.getVisibility() == View.GONE) {
                    continue;
                }
                int ll = left + mlp.leftMargin;
                int tt = top + mlp.topMargin;
                int rr = ll + childLineView.getMeasuredWidth();
                int bb = tt + childLineView.getMeasuredHeight();

                childLineView.layout(ll, tt, rr, bb);

                left += childLineView.getMeasuredWidth() + mlp.rightMargin + mlp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }
}
