package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    private int mWidth;
    private Paint mPaint = new Paint();
    private int mHeight;

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        //弧形
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF mArcLine = new RectF(mHeight * 0.4f, mHeight * 0.2f, mWidth * 0.7f, mHeight * 0.6f);
        //        startAngle   开始角度 已圆心为 0度   sweepAngle  增加多少度
        canvas.drawArc(mArcLine, -180, 60, false, mPaint);

        //圆心弧形
        mPaint.setStyle(Paint.Style.FILL);
        //从-110 度开始，绘制90个度数 也就是 -110° -- -20°  240°- 330°
        canvas.drawArc(mArcLine, -110, 90, true, mPaint);

        //非圆心弧形
        //从25度绘制130度 也就是  25° --- 155°
        canvas.drawArc(mArcLine, 25, 130, false, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
