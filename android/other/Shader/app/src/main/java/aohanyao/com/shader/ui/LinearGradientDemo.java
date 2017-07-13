package aohanyao.com.shader.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>作者：江俊超 on 2016/8/11 14:55</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class LinearGradientDemo extends View {
    public LinearGradientDemo(Context context) {
        super(context);
    }

    public LinearGradientDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.rotate(55f,getWidth()/2,getHeight()/2);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, getWidth(), getHeight(), new int[]{0xff03a9f4, 0xffff6600, 0xffffff00, 0xff00ff00}, null, Shader.TileMode.REPEAT);


        paint.setShader(linearGradient);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2 - 20, paint);
    }
}
