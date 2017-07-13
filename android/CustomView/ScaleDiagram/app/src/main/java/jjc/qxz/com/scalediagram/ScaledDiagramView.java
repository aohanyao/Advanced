package jjc.qxz.com.scalediagram;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * <p>作者：江俊超 on 2016/7/28 15:24</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>自定义的圆形图</p>
 */
public class ScaledDiagramView extends View {

    private int width;

    public ScaledDiagramView(Context context) {
        this(context, null);
    }

    public ScaledDiagramView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaledDiagramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int maxValue = 27;
    private float nowValue=27;
    @Override
    protected void onDraw(Canvas canvas) {

        float pre =  nowValue / (float) 100;

        int mCircularXY = width / 2;

        float mRadius = (float) (width * 0.5 / 2);
        super.onDraw(canvas);

        RectF mRectF = new RectF((float) (width * 0.1), (float) (width * 0.1), (float) (width * 0.9), (float) (width * 0.9));

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(30);
        paint1.setColor(0xff03a9f4);
        //从那个角度开始  到哪个角度结束

        Paint paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setStyle(Paint.Style.FILL);

        Paint paint3 = new Paint();
        paint3.setTextSize(50);
        paint3.setColor(0xffffffff);

        canvas.drawCircle(mCircularXY, mCircularXY, mRadius, paint2);
        canvas.drawArc(mRectF, 270, pre * 360, false, paint1);


        String textShow = (int)nowValue + "%";
        Rect bounds = new Rect();
        paint3.getTextBounds(textShow, 0, textShow.length(), bounds);
        canvas.drawText(textShow, mRectF.width()/2 ,mCircularXY  , paint3);
    }

    public void setMaxValue(final int maxValue){
        if (maxValue>0) {
            ValueAnimator valueAnimator=ValueAnimator.ofFloat(maxValue);
            valueAnimator.setDuration(1000);
            valueAnimator.setInterpolator(new OvershootInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedFraction = animation.getAnimatedFraction();
                    //Log.e("ss",String.valueOf(animatedFraction));
                    nowValue = (float)maxValue*animatedFraction;
                    postInvalidate();
                }
            });
            valueAnimator.start();
        }
    }
}
