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
        // 测量宽高和 宽高的模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //wrap_content  包裹内容下的宽高
        int wrapContentWidth = 0;
        int wrapContentHeight = 0;

        //获得自View的个数
        int childCount = getChildCount();
        //行高
        int linerHeight = 0;
        int linerWidth = 0;
        for (int i = 0; i < childCount; i++) {
            // 遍历 所有的子view
            View childView = getChildAt(i);
            //测量子view的宽高与模式
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //获取子view的布局参数
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            //获取子view的宽和搞
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //判断当前的行宽加上子view的宽度是不是大于了测量出来的快递
            if (linerWidth + childWidth > sizeWidth) {
                //取行宽和子view的最大值
                wrapContentWidth = Math.max(linerWidth, childWidth);
                //赋值下一行行宽
                linerWidth = childWidth;
                // 叠加当前高度，
                wrapContentHeight += linerHeight;
                //赋值下一行行高
                linerHeight = childHeight;
            } else {
                //没有大于宽度 则相加
                linerWidth += childWidth;
                //这时候的行高择取子view的最大值
                linerHeight = Math.max(linerHeight, childHeight);
            }
//            if (i == childCount - 1) {
//                //当子view是最后一个的时候 宽度取最大值，防止出现 一个的情况
//                wrapContentWidth = Math.max(linerHeight, childWidth);
//                //包裹内容情况下 高度相加
//                wrapContentHeight += linerHeight;
//            }
        }
        //设置值
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : wrapContentWidth, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : wrapContentHeight);
    }

    /**
     * 存放所有的行的view
     */
    private List<List<View>> mAllViews = new ArrayList<>();
    /**
     * 每行的高度
     */
    private List<Integer> mLineHieght = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        //获取当前宽度
        int width = getWidth();
        //行高与行宽
        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineViews = new ArrayList<>();
        int childCount = getChildCount();
        //遍历所有的子view
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            //获取子view高度与宽度
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin ;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            //判断子view的宽度加上累加的宽度是否大于整个ViewGroup的宽度
            if (childWidth+ lineWidth > width) {
                //加入到行高中
                mLineHieght.add(lineHeight);
                //当前行view加入集合
                mAllViews.add(lineViews);
                //初始化
                lineWidth = 0;
                lineViews = new ArrayList<>();
            }
            //累加宽度
            lineWidth += childWidth;
            //行高去最大值
            lineHeight = Math.max(lineHeight, childHeight);
            //加入集合
            lineViews.add(childView);
        }
        mLineHieght.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        //获取行数
        int lineNumber = mAllViews.size();
        for (int i = 0; i < lineNumber; i++) {
            //遍历行数的view
            lineViews = mAllViews.get(i);
            //获取行高
            lineHeight = mLineHieght.get(i);

            //遍历每一行的子view
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                if (childView.getVisibility() == GONE) {
                    continue;
                }
                //获取参数
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                //左边
                int lc = left + lp.leftMargin;
                //顶部
                int tc = top + lp.topMargin;
                //右边  左边加上宽度
                int rc = lc + childView.getMeasuredWidth();
                //下边  顶部加上高度
                int bc = tc + childView.getMeasuredHeight();
                //布局
                childView.layout(lc, tc, rc, bc);
                //左边距累加
                left += childView.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            }
            //初始化
            left = 0;
            //顶部累加行高
            top += lineHeight;
        }

    }
}
