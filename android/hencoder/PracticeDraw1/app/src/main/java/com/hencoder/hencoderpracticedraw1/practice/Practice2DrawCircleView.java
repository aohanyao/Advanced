package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {
    Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;
    private int mRadius;

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆

        //20 是间距

        //1.实心圆
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        int margin = 20;
        canvas.drawCircle(mRadius * 2.5f, mRadius + margin, mRadius, mPaint);

        //空心圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2f);
        canvas.drawCircle(mRadius * 5, mRadius + margin, mRadius, mPaint);

        //3.蓝色实心圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0x880000FF);
        canvas.drawCircle(mRadius * 2.5f, mRadius * 3.3f + margin, mRadius, mPaint);

        //4.线宽为 20 的空心圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(40f);//20dp  40px  没做单位换算，直接使用双倍
        canvas.drawCircle(mRadius * 5, mRadius * 3.3f + margin, mRadius, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mRadius = mWidth / 8;
    }
}
