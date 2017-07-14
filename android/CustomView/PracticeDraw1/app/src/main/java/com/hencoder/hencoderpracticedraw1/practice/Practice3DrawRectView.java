package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice3DrawRectView extends View {

    private int mWidthRadius;
    private int mWidth;
    private Paint mPaint = new Paint();
    private int mHeightRadius;
    private int mHeight;

    public Practice3DrawRectView(Context context) {
        super(context);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //mPaint.setStrokeWidth(2f);
        //mPaint.setStyle(Paint.Style.STROKE);

//        练习内容：使用 canvas.drawRect() 方法画矩形
        canvas.drawRect(mWidthRadius, mHeightRadius, mWidth - mWidthRadius, mHeight - mHeightRadius, mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidthRadius = w / 3;
        mHeightRadius = h / 4;
        mWidth = w;
        mHeight = h;
    }
}
