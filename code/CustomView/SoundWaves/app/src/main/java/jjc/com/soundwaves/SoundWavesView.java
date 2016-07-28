package jjc.com.soundwaves;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by 江俊超 on 2016/7/28.
 */
public class SoundWavesView extends View {
    private int mWidth, mRectHeight, mRectWidth;
    private int soundCound = 8;
    private Paint paint;
    private int offset;

    public SoundWavesView(Context context) {
        this(context, null);
    }

    public SoundWavesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoundWavesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        offset = 30;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRectHeight = getSize(heightMeasureSpec);
        mWidth = getSize(widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth * 0.6 / soundCound);

        LinearGradient linearGradient=new LinearGradient(
                0
                ,0
                ,mRectWidth
                ,mRectHeight
                ,Color.YELLOW,Color.BLUE
                , Shader.TileMode.CLAMP
        );
        paint.setShader(linearGradient);
    }

    private int getSize(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

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

    @Override
    protected void onDraw(Canvas canvas) {


        for (int i = 0; i < soundCound; i++) {
            float mCuuentHeight = (float) (mRectHeight * Math.random());
            canvas.drawRect(
                    (float) (mWidth * 0.4 / 2 + mRectWidth * i + offset)
                    , mCuuentHeight
                    , (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1))
                    , mRectHeight, paint);
        }
        super.onDraw(canvas);
        postInvalidateDelayed(300);
    }
}
