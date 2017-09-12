package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class Practice10HistogramView extends View {
    private int mWidth;
    private Paint mPaint = new Paint();
    private int mHeight;
    private int mHorizalMargin;
    private int mTopMargin = 30;
    private int mChatMargin = 10;
    private float mChatYAxisHeight;
    private float mChatWidth;
    private float mChatXAxisWidth;
    private int mChatCount = 7;
    private Random mRandom;
    private String[] mLabels = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    private Rect mTextRect = new Rect();

    public Practice10HistogramView(Context context) {
        super(context);

    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        //1. 绘制左边的竖线
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(18f);
        float strokeWidth = 2f;
        mPaint.setStrokeWidth(strokeWidth);
        canvas.drawLine(mHorizalMargin, mTopMargin, mHorizalMargin, mTopMargin + mChatYAxisHeight, mPaint);
        //2. 绘制下面的横线
        canvas.drawLine(mHorizalMargin,
                mTopMargin + mChatYAxisHeight,
                mChatXAxisWidth + mHorizalMargin,
                mTopMargin + mChatYAxisHeight,
                mPaint);

        //3. 绘制直方图
        for (int i = 0; i < mChatCount; i++) {
            mPaint.setColor(0x8800FF00);
            //X轴的坐标1
            float xAxis = mHorizalMargin + strokeWidth + ((i + 1) * mChatMargin) + mChatWidth * i;
            //当前随机出来的高度
            int mNowChatHeight = mRandom.nextInt((int) mChatYAxisHeight);
            //当前的Y轴坐标
            float yAxis = mChatYAxisHeight - mNowChatHeight + mTopMargin;
            //绘制直方图
            canvas.drawRect(xAxis,
                    yAxis,
                    xAxis + mChatWidth,
                    mChatYAxisHeight + mTopMargin - strokeWidth,
                    mPaint);


            mPaint.setColor(Color.WHITE);
            //绘制文字
            String label = mLabels[i];
            //测量文字的宽高
            mPaint.getTextBounds(label, 0, label.length(), mTextRect);
            float mTextSpace = mChatWidth - mTextRect.width();
            canvas.drawText(label,
                    xAxis + mTextSpace / 2,
                    mChatYAxisHeight + mTopMargin + 20,
                    mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mHorizalMargin = mWidth / 10;
        mChatYAxisHeight = mHeight * 0.7f;
        mChatXAxisWidth = mWidth * 0.8f;

        //计算每个条形图的宽度
        mChatWidth = (mChatXAxisWidth - mChatMargin * mChatCount) / (float) (mChatCount + 0.5);

        mRandom = new Random();
    }
}
