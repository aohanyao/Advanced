package aohanyao.com.camera.ui;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * <p>作者：江俊超 on 2016/8/19 13:43</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class CameraView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mCy;
    private int offset;
    private Camera mCamera;
    private Matrix mMatrix;
    private float mRotateX;
    private float mRotateY;
    private int centerX;
    private int centerY;

    private float canvasMaxRotateDegree = 40;
    private ValueAnimator steadyAnim;

    public CameraView(Context context) {
        this(context, null);
    }

    public CameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 圆角笔触
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureSize(widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
        int height = measureSize(heightMeasureSpec) + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff1782dd);
        rotate3d(canvas);
        canvas.save();
        for (int i = 0; i < 5; i++) {
            RectF rectF = new RectF(offset, offset + i * 5, mCy - offset, mCy - offset - i * 5);
            canvas.rotate(4, getWidth() / 2, getHeight() / 2);
            canvas.drawArc(rectF, 0, 360, false, mPaint);
            canvas.drawRect(rectF, mPaint);
        }
        canvas.restore();
        canvas.save();
    }

    /**
     * 旋转3D
     *
     * @param canvas
     */
    private void rotate3d(Canvas canvas) {
        mMatrix.reset();
        mCamera.save();
        mCamera.rotateX(mRotateY);
        mCamera.rotateY(mRotateX);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();
        mMatrix.preTranslate(-centerX, -centerY);
        mMatrix.postTranslate(centerX, centerY);
        canvas.concat(mMatrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                cancelSteadyAnimIfNeed();
                rotateCanvasWhenMove(x, y);
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                rotateCanvasWhenMove(x, y);
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                cancelSteadyAnimIfNeed();
                startNewSteadyAnim();
                invalidate();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private void cancelSteadyAnimIfNeed() {
        if (steadyAnim != null && (steadyAnim.isStarted() || steadyAnim.isRunning())) {
            steadyAnim.cancel();
        }
    }

    private void rotateCanvasWhenMove(float x, float y) {
        //       当前距离父类的控件的x 减去中心点
        float dx = x - (getWidth() / 2);
        float dy = y - (getHeight() / 2);
        float percentX = dx / (getWidth() / 2);
        float percentY = dy / (getHeight() / 2);
        // 中心的  100  50  = -50 -50/100 =   控制百分比
        if (percentX > 1f) {
            percentX = 1f;
        } else if (percentX < -1f) {
            percentX = -1f;
        }
        if (percentY > 1f) {
            percentY = 1f;
        } else if (percentY < -1f) {
            percentY = -1f;
        }

        mRotateX = canvasMaxRotateDegree * percentX;
        mRotateY = -(canvasMaxRotateDegree * percentY);
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

    private void startNewSteadyAnim() {
        final String propertyNameRotateX = "canvasRotateX";
        final String propertyNameRotateY = "canvasRotateY";

        PropertyValuesHolder holderRotateX = PropertyValuesHolder.ofFloat(propertyNameRotateX, mRotateX, 0);
        PropertyValuesHolder holderRotateY = PropertyValuesHolder.ofFloat(propertyNameRotateY, mRotateY, 0);
        steadyAnim = ValueAnimator.ofPropertyValuesHolder(holderRotateX, holderRotateY);
        steadyAnim.setDuration(1000);
        steadyAnim.setInterpolator(new BounceInterpolator());
        steadyAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotateX = (float) animation.getAnimatedValue(propertyNameRotateX);
                mRotateY = (float) animation.getAnimatedValue(propertyNameRotateY);
                invalidate();
            }
        });
        steadyAnim.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCy = Math.min(mWidth, mHeight);
        offset = (int) (mCy * 0.15);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
    }
}
