package aohanyao.com.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>作者：江俊超 on 2016/8/15 14:27</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class ClockView2 extends View {

    private int mRictWidth;// 圆的宽度
    private int offset = 20;

    public ClockView2(Context context) {
        this(context, null);
    }

    public ClockView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasured(widthMeasureSpec);
        int height = getMeasured(heightMeasureSpec);
        mRictWidth = Math.min(width, height);
    }

    /**
     * 测量
     *
     * @param measureSpec
     * @return
     */
    private int getMeasured(int measureSpec) {
        int reslut;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            reslut = size;
        } else {
            reslut = 200;
            if (mode == MeasureSpec.AT_MOST) {
                reslut = Math.min(size, reslut);
            }
        }

        return reslut;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        initPointer(canvas);
        postInvalidateDelayed(1000);
    }

    /**
     * @param canvas
     */
    private void initPointer(Canvas canvas) {
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        int minute = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
        int send = Integer.parseInt(new SimpleDateFormat("ss").format(new Date()));
        canvas.save();
        float hourDeger = (hour / 12f * 360) + (15 / 60f * minute);
        Paint mHourPaint = new Paint();
        mHourPaint.setAntiAlias(true);
        mHourPaint.setColor(Color.BLUE);
        canvas.rotate(hourDeger, mRictWidth / 2, mRictWidth / 2);
        int mHourOffsetTop = (int) (mRictWidth / 2f * 0.5);
        Rect mHourRect = new Rect(mRictWidth / 2 - 10, mHourOffsetTop, mRictWidth / 2 + 10, mRictWidth / 2 + 50);
        canvas.drawRect(mHourRect, mHourPaint);
        canvas.restore();
        canvas.save();
        float minuteDeger = minute / 60f * 360;
        Paint mMinutePaint = new Paint();
        mMinutePaint.setAntiAlias(true);
        mMinutePaint.setColor(Color.YELLOW);
        int minuteOffset = (int) (mRictWidth / 2f * 0.4);
        canvas.rotate(minuteDeger, mRictWidth / 2, mRictWidth / 2);
        Rect mMinuteRect = new Rect(mRictWidth / 2 - 7, minuteOffset, mRictWidth / 2 + 7, mRictWidth / 2 + 60);
        canvas.drawRect(mMinuteRect, mMinutePaint);
        canvas.restore();
        canvas.save();
        float sendDeger = send / 60f * 360;
        canvas.rotate(sendDeger, mRictWidth / 2, mRictWidth / 2);
        Paint mSendPaint = new Paint();
        mSendPaint.setColor(Color.RED);
        mSendPaint.setAntiAlias(true);
        Rect mSendRect = new Rect(mRictWidth / 2 - 5, (int) (mRictWidth / 2f * 0.2), mRictWidth / 2 + 5, mRictWidth / 2 + 70);
        canvas.drawRect(mSendRect, mSendPaint);
        canvas.restore();
        canvas.save();

        Paint mCirclePaint=new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        canvas.drawCircle(mRictWidth/2,mRictWidth/2,30,mCirclePaint);
        canvas.restore();
        canvas.save();
    }

    /**
     * 画背景 圆框 指针
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        //-------------------------外圈
        Paint mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(10);
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setAntiAlias(true);
        canvas.drawCircle(mRictWidth / 2, mRictWidth / 2, mRictWidth / 2 - offset, mCirclePaint);
        //-------------------------外圈

        //-------------------------刻度值
        Paint mDialsPaint = new Paint();
        mDialsPaint.setAntiAlias(true);
        for (int i = 0; i < 60; i++) {

            if (i % 5 == 0) {
                String str = String.valueOf(i / 5);
                if (i == 0) str = "12";
                mDialsPaint.setStrokeWidth(5);
                mDialsPaint.setColor(Color.RED);
                mDialsPaint.setTextSize(40);
                canvas.drawLine(mRictWidth / 2, offset, mRictWidth / 2, 60, mDialsPaint);
                canvas.drawText(str, mRictWidth / 2 - mDialsPaint.measureText(str) / 2, 100, mDialsPaint);
            } else {
                mDialsPaint.setStrokeWidth(3);
                mDialsPaint.setTextSize(15);
                mDialsPaint.setColor(Color.BLACK);
                canvas.drawLine(mRictWidth / 2, offset, mRictWidth / 2, 20, mDialsPaint);
            }
            canvas.drawLine(mRictWidth / 2, offset, mRictWidth / 2, 50, mDialsPaint);
            canvas.rotate(6, mRictWidth / 2, mRictWidth / 2);
        }
        //-------------------------刻度值
    }
}
