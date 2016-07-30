package aohanyao.com.flowlayoutlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>作者：江俊超 on 2016/7/30 13:43</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>流式布局</p>
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //wrap_content
        int width = 0;
        int height = 0;

        //获得自View的个数
        int childCount = getChildCount();
        //行高
        int linerHehght = 0;
        int linerWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View chilView = getChildAt(i);
            measureChild(chilView, widthMeasureSpec, heightMeasureSpec);
            //获取子view的布局参数
            MarginLayoutParams lp = (MarginLayoutParams) chilView.getLayoutParams();
            int childWidth = chilView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = chilView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (linerWidth + childWidth > sizeWidth) {
                width = Math.max(linerWidth, childWidth);
                linerWidth = childWidth;
                linerHehght = childHeight;
            } else {
                linerWidth += childWidth;
                linerHehght = Math.max(linerHehght, childHeight);
            }
            if (i == childCount - 1) {
                width = Math.max(linerHehght, childWidth);
                height += linerHehght;
            }
        }
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
    }

    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> mLineHieght = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width) {
                mLineHieght.add(lineHeight);
                mAllViews.add(lineViews);
                lineHeight = 0;
                lineViews = new ArrayList<>();
            }
            lineWidth += childWidth + lp.rightMargin + lp.leftMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(childView);
        }
        mLineHieght.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        int lineNumber = mAllViews.size();
        for (int i = 0; i < lineNumber; i++) {
            lineViews = mAllViews.get(i);
            lineHeight = mLineHieght.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                if (childView.getVisibility() == GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + childView.getMeasuredState();
                int bc = tc + childView.getMeasuredHeight();
                childView.layout(lc, tc, rc, bc);
                left += childView.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }

    }
}
