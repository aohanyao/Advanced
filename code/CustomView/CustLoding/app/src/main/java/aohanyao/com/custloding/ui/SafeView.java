package aohanyao.com.custloding.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * <p>作者：江俊超 on 2016/8/17 17:04</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class SafeView extends View {
    private int mHeight;
    private int mWidth;
    private int mCy;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private ValueAnimator animator;
    private float aninatopValue;
    private final int OFFSET = 50;

    public SafeView(Context context) {
        this(context, null);
    }

    public SafeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SafeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mLinearGradient=new LinearGradient()
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureSize(widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
        int height = measureSize(heightMeasureSpec) + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(width, height);
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCy = Math.min(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff1782dd);
        int radius = mCy / 2;
        float angle;
        if (aninatopValue < 360f) {
            angle = aninatopValue;
        } else {
            int circleCount = (int) (aninatopValue / 360);
            angle = aninatopValue - (circleCount * 360f);
        }
        canvas.rotate(angle, radius, radius);

        //画一个圆
        mPaint.setColor(0xfff6f6f6);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        canvas.drawCircle(mCy / 2, mCy / 2, mCy / 2 - OFFSET, mPaint);
        //移动坐标到中心
        canvas.translate(mCy / 2, mCy / 2);
        //左边的圆
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        canvas.drawCircle(-radius + OFFSET, 0, 10, mPaint);

        //右边的圆
        canvas.drawCircle(radius - 50, 0, 10, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        //绘制坐标的圆的尾巴
        RectF mRectF = new RectF(-radius + OFFSET, -radius + OFFSET, radius - OFFSET, radius - OFFSET);
//        canvas.drawRect(mRectF,mPaint);
        canvas.drawArc(mRectF, 90, 90, false, mPaint);//妈的 那是 从多少度 增加多少度
        canvas.drawArc(mRectF, 270, 90, false, mPaint);

        canvas.restore();
    }

    /**
     * 开始动画
     *
     * @param circleCount
     * @param duration
     */
    public void start(final int circleCount, final int duration) {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator.start();
        } else {
            animator = ValueAnimator.ofFloat(circleCount * 360f).setDuration(duration);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    aninatopValue = (float) animation.getAnimatedValue();
                    postInvalidate();
                    if (aninatopValue >= circleCount * 360f) {
                        startLoopAnimator();
                    }
                }
            });
            animator.start();
        }
    }

    private void startLoopAnimator() {
        animator = ValueAnimator.ofFloat(360f).setDuration(2000);
//        animator.setInterpolator(new DecelerateInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                aninatopValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
