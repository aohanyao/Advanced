package aohanyao.com.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>作者：江俊超 on 2016/8/8 14:51</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>Dashboard 刻度盘</p>
 */
public class ClockView extends View {

    private int mCircleWidth;
    private final int offset = 20;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mCircleWidth = Math.min(measureSize(heightMeasureSpec), measureSize(widthMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 测量
     *
     * @param spec
     * @return
     */
    private int measureSize(int spec) {
        int result;
        int size = MeasureSpec.getSize(spec);//获取大小
        int mode = MeasureSpec.getMode(spec);//获取模式
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 100;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        canvasBackground(canvas);
        //绘制指针
        canvasPointer(canvas);
        //一秒后重新绘制
        postInvalidateDelayed(1000);
    }

    private void canvasPointer(Canvas canvas) {
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        int minute = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
        int second = Integer.parseInt(new SimpleDateFormat("ss").format(new Date()));

        //------------------绘制时针
        canvas.save();//保存画布
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(15);//宽度
        paintHour.setColor(0xffff6600);//颜色 红色
        paintHour.setAntiAlias(true);//抗锯齿
        //当前小时百分比的度数  分钟在一个小时内的度数
        float hourDegree = (hour / 12f * 360) + (minute / 60f * 15/*一个小时是15度*/);
        int hourWidth = (int) (mCircleWidth / 2 * 0.5/*时钟半径的一半，也就是长度*/);
        canvas.rotate(hourDegree, mCircleWidth / 2, mCircleWidth / 2);//旋转角度
        canvas.drawLine(mCircleWidth / 2,
                mCircleWidth / 2 - hourWidth,
                mCircleWidth / 2,
                mCircleWidth / 2 + offset + 10/*offset + 10 是为了突出指针，不至于难看*/,
                paintHour);
        //当前画布与保存的画布合并
        canvas.restore();
        //------------------绘制时针

        //------------------绘制分针
        canvas.save();//保存画布
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        paintMinute.setAntiAlias(true);
        float minuteDegree = minute / 60f * 360;
        int minuteWidth = (int) (mCircleWidth / 2 * 0.7);
        canvas.rotate(minuteDegree, mCircleWidth / 2, mCircleWidth / 2);
        canvas.drawLine(mCircleWidth / 2,
                mCircleWidth / 2 - minuteWidth,
                mCircleWidth / 2,
                mCircleWidth / 2 + offset * 2,
                paintMinute);
        //当前画布与保存的画布合并
        canvas.restore();
        //------------------绘制分针


        //----------------------秒针
        Paint paintSecond = new Paint();
        paintSecond.setStrokeWidth(7);
        paintSecond.setAntiAlias(true);
        paintSecond.setColor(0xffff0000);
        canvas.save();
        float secondDegree = second / 60f * 360;//秒针  15秒的 度数
        canvas.rotate(secondDegree, mCircleWidth / 2, mCircleWidth / 2);
        int secondWidth = (int) (mCircleWidth / 2 * 0.9);//获得秒针的长度
        canvas.drawLine(mCircleWidth / 2,
                mCircleWidth / 2 - secondWidth,
                mCircleWidth / 2,
                mCircleWidth / 2 + offset * 3,
                paintSecond);
        //当前画布与保存的画布合并
        canvas.restore();
        //----------------------秒针

        //---------------------绘制秒针上面的圆圈
        canvas.save();
        Paint pinPaintCircle = new Paint();
        pinPaintCircle.setColor(0xffff0000);
        pinPaintCircle.setAntiAlias(true);
        canvas.drawCircle(mCircleWidth / 2, mCircleWidth / 2, offset, pinPaintCircle);
        canvas.restore();
        //---------------------绘制秒针上面的圆圈
    }

    private void canvasBackground(Canvas canvas) {
        //外部圆
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);//圆环
        paintCircle.setAntiAlias(true);//抗锯齿
        paintCircle.setStrokeWidth(5);//圆环宽度
        canvas.drawCircle(mCircleWidth / 2, mCircleWidth / 2, mCircleWidth / 2 - offset/*减去20的原因是中心点留白*/, paintCircle);

        //刻度线
        Paint paintDegree = new Paint();
        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                paintDegree.setStrokeWidth(5);// 小时
                paintDegree.setTextSize(30);
                canvas.drawLine(mCircleWidth / 2, offset/*绘制外圆减去的值*/, mCircleWidth / 2, 60/*刻度线的长度*/, paintDegree);
                String degree = "12";
                if (i != 0)
                    degree = String.valueOf(i / 5);
                canvas.drawText(degree, mCircleWidth / 2 - paintDegree.measureText(degree) / 2, 90/*字体的高度*/, paintDegree);
            } else {
                paintDegree.setStrokeWidth(3);//秒
                paintDegree.setTextSize(15);
                canvas.drawLine(mCircleWidth / 2, offset/*绘制外圆减去的值*/, mCircleWidth / 2, 30/*刻度线的长度*/, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mCircleWidth / 2 - paintDegree.measureText(degree) / 2, 60/*字体的高度*/, paintDegree);
            }
            // 旋转六度，360度/60秒  以时钟的中心点进行旋转
            canvas.rotate(6, mCircleWidth / 2, mCircleWidth / 2);
        }
    }
}
