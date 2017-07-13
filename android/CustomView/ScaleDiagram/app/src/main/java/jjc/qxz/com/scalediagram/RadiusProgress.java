package jjc.qxz.com.scalediagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>作者：江俊超 on 2016/7/28 16:29</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>圆形进度条</p>
 */
public class RadiusProgress extends View{

    private int mWidth;

    public RadiusProgress(Context context) {
        this(context,null);
    }

    public RadiusProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadiusProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        Paint mArcPaint = new Paint();
        mArcPaint.setColor(Color.BLUE);
        mArcPaint.setStyle(Paint.Style.STROKE);
        LinearGradient linearGradient=new LinearGradient(0,0,30,0,new int[]{Color.BLACK,Color.BLUE,Color.RED,Color.GREEN},null, Shader.TileMode.CLAMP);
        mArcPaint.setShader(linearGradient);
        mArcPaint.setStrokeWidth(30);
        RectF mArc = new RectF(100,100,(float)( mWidth-100),(float)( mWidth-100));
        canvas.drawArc(mArc,250,180,false,mArcPaint);
        super.onDraw(canvas);
    }
}
