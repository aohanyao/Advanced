package com.aohanyao.histogram.lib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>作者：江俊超 on 2016/7/29 09:46</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>七天，一个星期的柱状图</p>
 */
public class WeekHistogram extends View {
    /**
     * 每个之间的偏移量
     */
    private final int offset = 20;
    /**
     * 柱状图的个数
     */
    private final int mHistogramCount = 7;
    /**
     * 整个View的宽度
     */
    private int mWidth;
    /**
     * 整个View的高度
     */
    private int mHistogramHeight;
    /**
     * 每个柱状图的宽度
     */
    private int mHistogramWidth;
    private final int FORNT_COLOR = 0xff77b3d7;
    private final int BACK_COLOR = 0xffcccccc;
    private Paint mBackGround;
    private Paint mFrntPaint;
    private List<WeeData> weeDatas;
    private Paint mTextPaint;
    private ValueAnimator valueAnimator;
    private float animatedFraction;
    private int maxHeight;
    private int BOTTOM_HEIGHT;
    private final int PADDING_LEFT = offset;
    private Paint mLinearPaint;

    public WeekHistogram(Context context) {
        this(context, null);
    }

    public WeekHistogram(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekHistogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFrntPaint = new Paint();
        mFrntPaint.setColor(FORNT_COLOR);
        mLinearPaint = new Paint();
        mLinearPaint.setColor(FORNT_COLOR);
        mBackGround = new Paint();
        mBackGround.setColor(BACK_COLOR);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = measureSize(widthMeasureSpec);
        maxHeight = measureSize(heightMeasureSpec);
        BOTTOM_HEIGHT = maxHeight / 8;
        mHistogramHeight = maxHeight - BOTTOM_HEIGHT;
        mHistogramWidth = (int) ((mWidth - PADDING_LEFT) * 0.8 / mHistogramCount);
        weeDatas = new ArrayList<>();
        initWeekData();
    }

    /**
     * 初始化数据
     */
    private void initWeekData() {
        weeDatas.clear();
        for (int i = 0; i < mHistogramCount; i++) {
            float mCuuentHeight = (float) (mHistogramHeight * Math.random());
            int mBackCurentHeight = (int) (mHistogramHeight * Math.random());
            while (mBackCurentHeight < mCuuentHeight) {
                mBackCurentHeight = (int) (mHistogramHeight * Math.random());
            }
            weeDatas.add(new WeeData((int) mCuuentHeight , mBackCurentHeight  ));
        }
        valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(800);
        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedFraction = animation.getAnimatedFraction();
                postInvalidate();
            }
        });
        valueAnimator.start();
        postInvalidateDelayed(500);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        //绘制背景
        drawBackground(canvas);
        //绘制柱状图
        drawHistogarm(canvas);
        super.onDraw(canvas);
        canvas.restore();

    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        //------------底部的线
        Rect mBottomLinearRect = new Rect(
                offset,
                mHistogramHeight + offset - 3
                , mWidth
                , mHistogramHeight + offset
        );
        canvas.drawRect(mBottomLinearRect, mLinearPaint);
        //------------底部的线

        //--------------左边的线
        Rect mLeftLinearRect = new Rect(
                offset
                , 3
                , offset + 3
                , mHistogramHeight + offset
        );
        canvas.drawRect(mLeftLinearRect, mLinearPaint);
        //--------------左边的线

        //---------------顶部的背景线
        canvas.drawLine(offset, offset, mWidth, offset, mBackGround);
        canvas.drawLine(offset, mHistogramHeight / 2, mWidth, mHistogramHeight / 2, mBackGround);

        //---------------顶部的背景线
    }

    /**
     * 绘制柱状图
     *
     * @param canvas
     */
    private void drawHistogarm(Canvas canvas) {
        for (int i = 0; i < mHistogramCount; i++) {
            WeeData weeData = weeDatas.get(i);
            float mCuuentHeight;
            float mBackCurentHeight;

            if (valueAnimator.isRunning()) {
                mCuuentHeight = weeData.getP() * animatedFraction;
                mBackCurentHeight = weeData.getB() * animatedFraction;
            } else {
                mCuuentHeight = weeData.getP();
                mBackCurentHeight = weeData.getB();
            }
            int pre = (int) ((mCuuentHeight / mBackCurentHeight) * 100);
            //---------绘制背景
            Rect mBackgroundRect = new Rect(
                    (i + 1) * offset + mHistogramWidth * i + PADDING_LEFT
                    , (int) (mHistogramHeight - mBackCurentHeight+offset)
                    , ((i + 1) * offset) + ((i + 1) * mHistogramWidth) + PADDING_LEFT
                    , mHistogramHeight - offset * 2);
            canvas.drawRect(mBackgroundRect, mBackGround);
            //---------绘制背景

            //绘制前景
            Rect rect = new Rect(
                    (i + 1) * offset + mHistogramWidth * i + PADDING_LEFT
                    , (int) (mHistogramHeight - mCuuentHeight+offset)
                    , ((i + 1) * offset) + ((i + 1) * mHistogramWidth) + PADDING_LEFT
                    , mHistogramHeight - offset * 2);
            canvas.drawRect(rect, mFrntPaint);
            String str = String.valueOf(pre) + "%";

            canvas.drawText(str, 0, str.length(), (i + 1) * offset + mHistogramWidth * i + ((mHistogramWidth + PADDING_LEFT) / 2), mHistogramHeight, mTextPaint);
            //绘制前景
        }
    }

    /**
     * 测量数据
     *
     * @param measureArg
     * @return
     */
    private int measureSize(int measureArg) {
        int result;
        int mode = MeasureSpec.getMode(measureArg);
        int size = MeasureSpec.getSize(measureArg);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 200;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    public void readLoad() {
        initWeekData();

    }
}
