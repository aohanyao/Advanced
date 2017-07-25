package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice13ShadowLayerView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice13ShadowLayerView(Context context) {
        super(context);
    }

    public Practice13ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

        // 使用 Paint.setShadowLayer() 设置阴影
        paint.setShadowLayer(10, 0, 0, 0xff03a4f9);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(120);
        canvas.drawText("Hello HenCoder", 50, 200, paint);

        paint.setStrokeWidth(20);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(50, 250, 300, 400, 0, 360, false, paint);
    }
}
