package com.aohanyao.coolmenu.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;

import com.aohanyao.coolmenu.R;

/**
 * <p>作者：江俊超 on 2016/8/26 15:40</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class CoolMenu extends View {
    private int mCenterMenuColor = 0xff288aff;
    private int mMenuBackgroundColor = 0xff7f65ff;
    private Paint mPaint;
    private ValueAnimator mAnimatorBackground;
    private int mCenterMenuOffset = 20;
    private int mHeight;
    private int mWidth;
    private int mCenterMenuRadius;
    private int mMenuBackgroundOffset;
    private int mForkLenght;
    private boolean isOpen = false;
    private float mPreValue;
    private Rect mCenterMenuRect;
    private Rect mMenu1;
    private Rect mMenu2;
    private Rect mMenu3;
    private Rect mMenu4;
    private Bitmap mMenu1Bitmap;
    private Bitmap mMenu2Bitmap;
    private Bitmap mMenu3Bitmap;
    private Bitmap mMenu4Bitmap;
    private int menuAnimCount = 1;
    private float mMenuPre1;
    private ValueAnimator mAnimatorMenu;
    String TAG = "CoolMenu";
    private float mMenuPre2;
    private float mMenuPre3;
    private float mMenuPre4;

    public CoolMenu(Context context) {
        super(context);
        initSomeThing();
    }

    public CoolMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSomeThing();
    }

    public CoolMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSomeThing();
    }

    /**
     * 初始化画笔等
     */
    private void initSomeThing() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 菜单背景 start
        int left = mWidth / 2;//100
        float nowWidth = (left - mMenuBackgroundOffset / 2) * mPreValue;
        RectF mMenuRectF = new RectF(left - nowWidth, mMenuBackgroundOffset, left + nowWidth, mHeight - mMenuBackgroundOffset);
        mPaint.setColor(mMenuBackgroundColor);
        canvas.drawRoundRect(mMenuRectF, mCenterMenuRadius, mCenterMenuRadius, mPaint);
        // 菜单背景 end
        canvasMenu(canvas);
        //画中心的圆 start
        mPaint.setShadowLayer(7, 4, 5, 0xff999999);//设置阴影图层
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setColor(mCenterMenuColor);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mCenterMenuRadius, mPaint);
        mPaint.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        canvas.rotate(360 * mPreValue, mWidth / 2, mHeight / 2);

        //画中心的圆 end
        //圆形菜单中的 叉叉
        canvas.translate(mWidth / 2, mHeight / 2);//移动坐标点
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(mForkLenght * mPreValue, -mForkLenght, -mForkLenght, mForkLenght, mPaint);
        canvas.drawLine(-mForkLenght * mPreValue, -mForkLenght, mForkLenght, mForkLenght, mPaint);
    }

    private void canvasMenu(Canvas canvas) {
        //绘制四个菜单的矩阵start
        int d = mWidth / 2 - mCenterMenuRadius / 2;//以中心切分的距离 减去圆的半径的长度
        int c = mWidth / 2 + mCenterMenuRadius / 2;//菜单2开始的位置
        int menuWidth = Math.min(d / 5, (int) (mHeight * 0.7));//每个圆心
        int menuOffset = (mHeight - menuWidth) / 2;
        mPaint.setColor(Color.WHITE);

        //-----按钮1的位置
        //-----------高度
        //高度比例
        int h1 = (int) (((mHeight / 2) - menuOffset) * mMenuPre4);
        //顶部
        int mT1 = (mHeight / 2) - h1;
        //底部
        int mB1 = (mHeight / 2) + h1;
        //-----------高度
        //宽度比例
        int v1 = (int) (menuWidth / 2 * mMenuPre4);//矩形一半
        int m1L = (menuWidth * 2) - v1;
        int m1R = (menuWidth * 2) + v1;
        mMenu1 = new Rect(m1L, mT1, m1R, mB1);
        canvas.drawRect(mMenu1, mPaint);
        //-----按钮1的位置

        //-----------高度
        //高度比例
        int h2 = (int) (((mHeight / 2) - menuOffset) * mMenuPre3);
        //顶部
        int mT2 = (mHeight / 2) - h2;
        //底部
        int mB2 = (mHeight / 2) + h2;
        //-----------高度
        //宽度比例
        int v2 = (int) (menuWidth / 2 * mMenuPre3);//矩形一半
        //-----按钮2的位置
        int m2L = (int) ((menuWidth * 3.5) - v2);
        int m2R = (int) ((menuWidth * 3.5) + v2);
        mMenu2 = new Rect(m2L, mT2, m2R, mB2);
        canvas.drawRect(mMenu2, mPaint);
        //-----按钮2的位置

        //-----------高度
        //高度比例
        int h3 = (int) (((mHeight / 2) - menuOffset) * mMenuPre2);
        //顶部
        int mT3 = (mHeight / 2) - h3;
        //底部
        int mB3 = (mHeight / 2) + h3;
        //-----------高度
        //宽度比例
        int v3 = (int) (menuWidth / 2 * mMenuPre2);//矩形一半
        //-----按钮3的位置
        int m3L = (int) ((c + menuWidth * 1.5) - v3);
        int m3R = (int) ((c + menuWidth * 1.5) + v3);
        mMenu3 = new Rect(m3L, mT3, m3R, mB3);
        canvas.drawRect(mMenu3, mPaint);
        //-----按钮3的位置


        //-----------高度
        //高度比例
        int h4 = (int) (((mHeight / 2) - menuOffset) * mMenuPre1);

        //顶部
        int mT4 = (mHeight / 2) - h4;
        //底部
        int mB4 = (mHeight / 2) + h4;
        //-----------高度
        //宽度比例
        int v4 = (int) (menuWidth / 2 * mMenuPre1);//矩形一半
        //-----按钮4的位置
        int m4L = (c + menuWidth * 3) - v4;
        int m4R = (c + menuWidth * 3) + v4;
        mMenu4 = new Rect(m4L, mT4, m4R, mB4);
        canvas.drawRect(mMenu4, mPaint);
        //-----按钮4的位置
        //计算四个菜单的矩阵end

        //获取菜单
        mMenu1Bitmap = scaleBitmap(R.mipmap.order, menuWidth);
        mMenu2Bitmap = scaleBitmap(R.mipmap.score, menuWidth);
        mMenu3Bitmap = scaleBitmap(R.mipmap.set, menuWidth);
        mMenu4Bitmap = scaleBitmap(R.mipmap.shop, menuWidth);
//        canvas.drawBitmap(mMenu1Bitmap, mMenu1.left, mMenu1.top, mPaint);
//        canvas.drawBitmap(mMenu2Bitmap, mMenu2.left, mMenu2.top, mPaint);
//        canvas.drawBitmap(mMenu3Bitmap, mMenu3.left, mMenu3.top, mPaint);
//        canvas.drawBitmap(mMenu4Bitmap, mMenu4.left, mMenu4.top, mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = measureSize(widthMeasureSpec, getWindowMsg(getContext())[0] - getPaddingLeft() - getPaddingRight() - mCenterMenuOffset);
        int measureHeight = measureSize(heightMeasureSpec, dp2px(getContext(), 90)) + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;//宽度
        mHeight = h;//高度
        mCenterMenuOffset = (int) (mHeight * 0.3);//中间 圆形菜单的上下偏移量
        mMenuBackgroundOffset = (int) ((mHeight * 0.5) / 2); //紫色圆角矩形的上下偏移量
        mCenterMenuRadius = (mHeight - mCenterMenuOffset) / 2;//中间圆形的半径
        mForkLenght = (int) (mCenterMenuRadius * 0.4);//中间两个线的长度

        //得到中心菜单的矩阵 start
        int l = mWidth / 2 - mCenterMenuRadius;
        int t = mCenterMenuOffset / 2;
        int b = mHeight - mCenterMenuOffset / 2;
        int r = mWidth / 2 + mCenterMenuRadius;
        mCenterMenuRect = new Rect(l, t, r, b);//中心的圆的矩阵，用来响应点击事件的
        //得到中心菜单的矩阵 end
    }

    /**
     * 创建并缩放按钮
     *
     * @param resId
     * @param wd
     * @return
     */
    private Bitmap scaleBitmap(int resId, float wd) {
        Bitmap mMenuBitmap = BitmapFactory.decodeResource(getResources(), resId);
        int m = Math.max(mMenuBitmap.getWidth(), mMenuBitmap.getHeight());
        float w = wd / m;
        Matrix matrix = new Matrix();
        matrix.postScale(w, w);
        return Bitmap.createBitmap(mMenuBitmap, 0, 0, mMenuBitmap.getWidth(), mMenuBitmap.getHeight(), matrix, false);
    }

    /**
     * 打开或关闭
     */
    public void openOrClose() {
        if (isOpen) {
            startAnimator(1, 0);
        } else {
            startAnimator(0, 1);
        }
        isOpen = !isOpen;
    }

    /**
     * 开启启动动画
     *
     * @param from
     * @param end
     */
    private void startAnimator(final int from, int end) {
        if (mAnimatorBackground != null && mAnimatorBackground.isRunning()) {
            mAnimatorBackground.cancel();
            mAnimatorBackground = null;
        }
        if (mAnimatorMenu != null && mAnimatorMenu.isRunning()) {
            mAnimatorMenu.cancel();
            mAnimatorMenu = null;
        }
        mAnimatorBackground = ValueAnimator.ofFloat(from, end).setDuration(500);
        mAnimatorBackground.setInterpolator(new OvershootInterpolator());
        mAnimatorBackground.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPreValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAnimatorMenu = ValueAnimator.ofFloat(from, end).setDuration(200);
        mAnimatorMenu.setInterpolator(new OvershootInterpolator());
        mAnimatorMenu.setRepeatCount(4);
        mAnimatorMenu.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (menuAnimCount == 1)
                    mMenuPre1 = (float) animation.getAnimatedValue();
                if (menuAnimCount == 2)
                    mMenuPre2 = (float) animation.getAnimatedValue();
                if (menuAnimCount == 3)
                    mMenuPre3 = (float) animation.getAnimatedValue();
                if (menuAnimCount == 4)
                    mMenuPre4 = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAnimatorMenu.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                if (from == 1) {
                    menuAnimCount++;
                } else {
                    menuAnimCount--;
                }
                super.onAnimationRepeat(animation);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        if (from == 1) {
            mAnimatorMenu.setDuration(100);
            menuAnimCount = 1;
            animatorSet.play(mAnimatorBackground).after(mAnimatorMenu);
        } else {
            menuAnimCount = 4;
            animatorSet.play(mAnimatorMenu).after(mAnimatorBackground);
        }
        animatorSet.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();//获得点击的坐标
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                if (mCenterMenuRect.contains(x, y)) {//包含 打开菜单获关闭
                    openOrClose();
                }
                break;
        }
        return true;
    }

    /**
     * 获取屏幕宽高
     *
     * @return wh[1] 屏幕高度
     */
    private int[] getWindowMsg(Context context) {
        int[] wh = new int[2];
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wh[0] = wm.getDefaultDisplay().getWidth();
        wh[1] = wm.getDefaultDisplay().getHeight();
        //Log.d("vivi", "屏幕宽 =" + wh[0]);
        //Log.d("vivi", "屏幕高 =" + wh[1]);
        return wh;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 测量宽高 模式
     *
     * @param measureSpec
     * @return
     */
    private int measureSize(int measureSpec, int defaultSize) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = defaultSize;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
}
