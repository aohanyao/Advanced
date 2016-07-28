package jjc.com.customtextviewbackground1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 江俊超 on 2016/7/27.
 * <p>闪闪发亮的TextView</p>
 */
public class GradientTextView extends TextView {
    private int mWidth = 0;
    private Paint mPaint;
    private Matrix mAatrix;
    private int translate;
    private LinearGradient mLinearGradient;

    public GradientTextView(Context context) {
        this(context, null);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mWidth == 0) {
            mWidth = getMeasuredWidth();
            if (mWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,
                        0,
                        mWidth,
                        0,
                        new int[]{0x33ffffff, 0xffffffff, 0x33ffffff},
                        null
                        , Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mAatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAatrix != null) {
            translate += mWidth / 5;
            if (translate > 2 * mWidth) {
                translate = -mWidth;
            }
            mAatrix.setTranslate(translate, 0);
            mLinearGradient.setLocalMatrix(mAatrix);
            postInvalidateDelayed(100);
        }
    }
}
