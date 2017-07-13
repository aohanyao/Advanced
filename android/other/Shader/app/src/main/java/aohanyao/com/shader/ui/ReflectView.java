package aohanyao.com.shader.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>作者：江俊超 on 2016/8/11 15:11</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class ReflectView extends View {
    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXfermode;

    public ReflectView(Context context) {
        this(context, null);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        
        super.onDraw(canvas);
//        canvas.drawBitmap(createReflection(mSrcBitmap,0),0,0,mPaint);
    }

    /**
     * 生成 正影与倒影的bitmap
     *
     * @param original 原来的bitmap
     * @param gap
     * @return
     */
    private Bitmap createReflection(Bitmap original, int gap) {
        //倒影的高度
        final int reflectionHeight = original.getHeight() / 2;
        //根据正影生成倒影
        Bitmap bitmapWithReflection = Bitmap.createBitmap(original.getWidth(),
                (original.getHeight() + reflectionHeight + gap),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmapWithReflection);
        // 原来的
        canvas.drawBitmap(original, 0, 0, null);
        // 画笔
        Paint transparentPaint = new Paint();
        transparentPaint.setARGB(0, 255, 255, 255);
        //绘制正影
        canvas.drawRect(0, original.getHeight(), original.getWidth(), original.getHeight() + gap,
                transparentPaint);

        // 倒影
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        //绘制倒影
        canvas.drawBitmap(Bitmap.createBitmap(original, 0, original.getHeight() - reflectionHeight,
                original.getWidth(), reflectionHeight, matrix, false), 0, original.getHeight() + gap, null);


        // 绘制倒影透明度
        final Paint fadePaint = new Paint();
        //线性变化
        fadePaint.setShader(new LinearGradient(0, original.getHeight(), 0,
                original.getHeight() + reflectionHeight + gap, 0xddffffff, 0x00ffffff, Shader.TileMode.CLAMP));
        //模式
        fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //绘制倒影透明
        canvas.drawRect(0, original.getHeight(), original.getWidth(), bitmapWithReflection.getHeight() + gap, fadePaint);

        return bitmapWithReflection;
    }
}
