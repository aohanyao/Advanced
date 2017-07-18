package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Practice11PieChartView extends View {
    private int mWidth;
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private int mHeight;
    private float mRadius;
    private float offsetWidth = 10;
    /**
     * 间隙角度
     */
    private float offsetAngle = 1;
    private int mChatCount = 6;
    private Random mRandom = new Random();
    private List<Float> mAngelValues = new ArrayList<>();
    int colors[] = {0xfff44336, 0xffffc107, 0xff9c27b0, 0xff9e9e9e, 0xff009688, 0xff2196f3};
    private RectF mArcRectf;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        float sumOffsetAngle = mChatCount * offsetAngle;
        float laveAngel = 360f - sumOffsetAngle;

        float sumProcess = 0;
        //初始化相关  获取 每个值
        for (int i = 0; i < mChatCount; i++) {
            int nowProcess = mRandom.nextInt(100);
            mAngelValues.add((float) nowProcess);
            sumProcess += nowProcess;
        }
        //计算每个百分比
        for (int i = 0; i < mChatCount; i++) {
            float process = (mAngelValues.get(i) / sumProcess) * laveAngel;
            mAngelValues.set(i, process);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        /// 随机数版本的饼状图(canvas);
        普通版本的饼状图(canvas);

        //TODO 还没有绘制 线条和文字  还没有想明白坐标系应该怎么计算
    }

    private void 随机数版本的饼状图(Canvas canvas) {
        float startAngel = -180 - offsetAngle;
        // 普通版本的饼状图(canvas);
        for (int i = 0; i < mChatCount; i++) {
            //设置颜色
            mPaint.setColor(colors[i]);
            //开始绘制
            canvas.drawArc(mArcRectf, startAngel + offsetAngle, mAngelValues.get(i), true, mPaint);
            //设置下一个角度
            startAngel = mAngelValues.get(i) + startAngel + offsetAngle;
        }
    }

    private void 普通版本的饼状图(Canvas canvas) {
        //1.第一个饼
        //处于焦点的饼
        RectF mArcNow = new RectF(mWidth * 0.15f, 10, mWidth * 0.15f + mRadius, 10 + mRadius);
        //.绘制线和文字
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStrokeWidth(2f);
//        mPath.moveTo();

        mPaint.setColor(0xfff44336);
        canvas.drawArc(mArcNow, -180, 135, true, mPaint);


        //其他的扇形， 增加偏移10即可
        mArcNow.left = mArcNow.left + offsetWidth;
        mArcNow.top = mArcNow.top + offsetWidth;
        mArcNow.right = mArcNow.right + offsetWidth;
        mArcNow.bottom = mArcNow.bottom + offsetWidth;
        //其他的扇形， 增加偏移10即可


        //2.第二个饼
        mPaint.setColor(0xffffc107);
        canvas.drawArc(mArcNow, -45, 45, true, mPaint);

        //3.第三个饼
        mPaint.setColor(0xff9c27b0);
//        mArcNow.top = mArcNow.top + offsetAngle;
//        mArcNow.bottom = mArcNow.bottom + offsetAngle;
        canvas.drawArc(mArcNow, 1, 7, true, mPaint);


        //4.第四个饼
        mPaint.setColor(0xff9e9e9e);
//        mArcNow.top = mArcNow.top + offsetAngle;
//        mArcNow.bottom = mArcNow.bottom + offsetAngle;
        canvas.drawArc(mArcNow, 9, 7, true, mPaint);
        //5.第五个饼
        mPaint.setColor(0xff009688);
        canvas.drawArc(mArcNow, 17, 45, true, mPaint);
        //6.第六个饼
        mPaint.setColor(0xff2196f3);
        canvas.drawArc(mArcNow, 63, 117, true, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = mHeight * 0.8f;
        mArcRectf = new RectF(mWidth * 0.15f, 10, mWidth * 0.15f + mRadius, 10 + mRadius);
    }
}
