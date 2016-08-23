package aohanyao.com.kaleidoscope.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * <p>作者：江俊超 on 2016/8/19 10:16</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>万花筒？</p>
 */
public class KaleidoscopeView extends View {
    private int mWidth;
    private int mHeight;
    private int mRidus;
    private int mOffset;
    private Paint mPaint;
    private ValueAnimator animator;
    private float animatorValue;

    public KaleidoscopeView(Context context) {
        this(context, null);
    }

    public KaleidoscopeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KaleidoscopeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mOffset = (int) (Math.min(mWidth, mHeight) * 0.1);
        mRidus = (Math.min(mWidth, mHeight));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureSize(widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
        int height = measureSize(heightMeasureSpec) + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    /**
     * 测量宽高 模式
     *
     * @param measureSpec
     * @return
     */
    private int measureSize(int measureSpec) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
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
        super.onDraw(canvas);

        //绘制背景颜色
        canvas.drawColor(0xff1782dd);
        canvas.rotate(animatorValue, getWidth() / 2, getHeight() / 2);
//        canvas.translate(getWidth() / 2, getHeight() / 2);

        //绘制矩形
//        canvas.drawRect(rectF, mPaint);
//        //绘制椭圆
        for (int i = 0; i < 5; i++) {
            RectF rectF = new RectF(mOffset, mOffset + i * 5, mRidus - mOffset, mRidus - mOffset - i * 5);
            canvas.rotate(4, getWidth() / 2, getHeight() / 2);
            canvas.drawArc(rectF, 0, 360, false, mPaint);
//            canvas.drawRect(rectF, mPaint);

        }
        canvas.restore();
        canvas.save();

    }

    public void startAnimator() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
        } else {
            animator = ValueAnimator.ofFloat(360f).setDuration(1500);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animatorValue = (Float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.start();
        }
    }
}
