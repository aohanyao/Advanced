package aohanyao.com.event.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * <p>作者：江俊超 on 2016/8/15 16:30</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class ViewA extends View{
    private String TAG="ViewA";
    public ViewA(Context context) {
        super(context);
    }

    public ViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
