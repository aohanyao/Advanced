package jjc.com.customtextviewbackground1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 江俊超 on 2016/7/27.
 * <p>自定义文本，显示两种不同的背景</p>
 */
public class CustomTextViewBackground extends TextView{
    public CustomTextViewBackground(Context context) {
        this(context,null);
    }

    public CustomTextViewBackground(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTextViewBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint1=new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setStyle(Paint.Style.FILL);

        Paint paint2=new Paint();
        paint2.setColor(Color.YELLOW);
        paint2.setStyle(Paint.Style.FILL);

        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,paint2);
        super.onDraw(canvas);
    }
}
