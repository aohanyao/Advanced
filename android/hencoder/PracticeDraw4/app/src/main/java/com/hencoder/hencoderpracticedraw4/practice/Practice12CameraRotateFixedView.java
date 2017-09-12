package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(100, 100);
    Point point2 = new Point(400, 100);
    Camera camera = new Camera();
    Matrix matrix = new Matrix();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX1 = point1.x + bitmap.getWidth() / 2;
        int centerY1 = point1.y + bitmap.getHeight() / 2;

        camera.save();
        camera.rotateX(30);
        matrix.reset();
        camera.getMatrix(matrix);
        matrix.preTranslate(-centerX1, -centerY1);
        matrix.postTranslate(centerX1, centerY1);
        camera.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        int centerX2 = point2.x + bitmap.getWidth() / 2;
        int centerY2 = point2.y + bitmap.getHeight() / 2;

        camera.save();
        camera.rotateY(30);
        matrix.reset();
        camera.getMatrix(matrix);
        matrix.preTranslate(-centerX2, -centerY2);
        matrix.postTranslate(centerX2, centerY2);
        camera.restore();

        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
