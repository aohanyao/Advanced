package aohanyao.com.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>作者：江俊超 on 2016/8/8 14:51</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>Dashboard 刻度盘</p>
 */
public class DashboardView extends View {

    private int mCircleWidth;

    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        //外部圆
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);//圆环
        paintCircle.setAntiAlias(true);//抗锯齿
        paintCircle.setStrokeWidth(5);//圆环宽度
        canvas.drawCircle(mCircleWidth / 2, mCircleWidth / 2, mCircleWidth / 2 - 20/*减去20的原因是中心点留白*/, paintCircle);

        //刻度线
        Paint paintDegree = new Paint();
        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                paintDegree.setStrokeWidth(5);// 小时
                paintDegree.setTextSize(30);
                canvas.drawLine(mCircleWidth / 2, 20/*绘制外圆减去的值*/, mCircleWidth / 2, 60/*刻度线的长度*/, paintDegree);
                String degree = "12";
                if (i != 0)
                    degree = String.valueOf(i / 5);
                canvas.drawText(degree, mCircleWidth / 2 - paintDegree.measureText(degree) / 2, 90/*字体的高度*/, paintDegree);
            } else {
                paintDegree.setStrokeWidth(3);//秒
                paintDegree.setTextSize(15);
                canvas.drawLine(mCircleWidth / 2, 20/*绘制外圆减去的值*/, mCircleWidth / 2, 30/*刻度线的长度*/, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mCircleWidth / 2 - paintDegree.measureText(degree) / 2, 60/*字体的高度*/, paintDegree);
            }
            // 旋转六度，360度/60秒  以时钟的中心点进行旋转
            canvas.rotate(6, mCircleWidth / 2, mCircleWidth / 2);
        }

        //画针
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);

        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        Paint paintSecond = new Paint();
        paintSecond.setStrokeWidth(7);
        canvas.save();
        // 将 x y 点移到 中心
        canvas.translate(mCircleWidth / 2, mCircleWidth / 2);
        //float startX, float startY, float stopX, float stopY
        canvas.drawLine(0, 0, 100, 100/*暂时写死*/, paintHour);
        canvas.drawLine(0, 0, 100, 280, paintSecond);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        //合并 画布
        canvas.restore();
    }
}
