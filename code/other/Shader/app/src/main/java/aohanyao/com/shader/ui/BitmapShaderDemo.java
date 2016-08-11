package aohanyao.com.shader.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import aohanyao.com.shader.R;

/**
 * <p>作者：江俊超 on 2016/8/11 14:45</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class BitmapShaderDemo extends View {
    private Bitmap mBitmap;

    public BitmapShaderDemo(Context context) {
        this(context, null);
    }

    public BitmapShaderDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon);
        BitmapShader mBitmapShader = new BitmapShader(
                mBitmap, Shader.TileMode.CLAMP
                , Shader.TileMode.CLAMP
        );
        Paint paint = new Paint();
        paint.setShader(mBitmapShader);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2 - 20, paint);
    }
}
