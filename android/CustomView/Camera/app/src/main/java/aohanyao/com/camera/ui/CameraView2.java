package aohanyao.com.camera.ui;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * <p>作者：江俊超 on 2016/8/19 15:42</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class CameraView2 extends View {

    private Camera mCamera;
    private Matrix mCanvasMatrix;
    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private float mRoteX;
    private float mRoteY;
    private float mRoteZ;
    private float centerX;
    private float centerY;
    private ValueAnimator animator;

    public CameraView2(Context context) {
        this(context, null);
    }

    public CameraView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);

        mCanvasMatrix = new Matrix();
        mCamera = new Camera();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        centerX = mWidth / 2;
        centerY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRotate(canvas);
        canvas.drawColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        int ridus = mWidth / 5;
        canvas.drawRect(ridus, ridus, ridus * 3, ridus * 3, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cancelAnimator();
                postPosition(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                postPosition(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                startBackAnimator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void startBackAnimator() {
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("x", mRoteX, 0);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("y", mRoteY, 0);
        animator = ValueAnimator.ofPropertyValuesHolder(holderX, holderY);
        animator.setDuration(800);
        animator.setInterpolator(new BounceInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRoteX = (float) animation.getAnimatedValue("x");
                mRoteY = (float) animation.getAnimatedValue("y");
                invalidate();
            }
        });
        animator.start();
    }

    /**
     * 取消动画
     */
    private void cancelAnimator() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
            mRoteX = 0;
            mRoteY = 0;
        }
    }

    private void postPosition(float x, float y) {
        float dx = x - mWidth / 2;
        float dy = y - mHeight / 2;

        float perX = dx / (mWidth / 2);
        float perY = dy / (mHeight / 2);

        if (perX > 1f) {
            perX = 1f;
        } else if (perX < -1f) {
            perX = -1f;
        }
        if (perY > 1f) {
            perY = 1f;
        } else if (perY < -1f) {
            perY = -1f;
        }
        mRoteX = 60 * perX;
        mRoteY = -(60 * perY);
    }

    private void drawRotate(Canvas canvas) {
        mCanvasMatrix.reset();
        mCamera.save();
        mCamera.rotateX(mRoteY);
        mCamera.rotateY(mRoteX);
        mCamera.getMatrix(mCanvasMatrix);
        mCamera.restore();
        mCanvasMatrix.preTranslate(-centerX, -centerY);
        mCanvasMatrix.postTranslate(centerX, centerY);
        canvas.concat(mCanvasMatrix);
    }
}
