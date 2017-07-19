package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    private int mWidth;
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private int mHeight;

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2f);
//        练习内容：使用 canvas.drawPath() 方法画心形
        //1.移动到中间
//        mPath.moveTo(mWidth * 0.4f, 100);
        //2.先绘制左边的圆

        //绘制右边的圆
        mPath.addArc(200, 0, 400, 200, -225, 225);
        mPath.arcTo (400, 0, 600, 200,  -180, 225, false);
        //mPath.addArc(200, 20, 400, 220, -225, 225);
//        mPath.arcTo (400, 20, 600, 220, -180, 225, false);
        mPath.lineTo(400,342);
        canvas.drawPath(mPath, mPaint);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
