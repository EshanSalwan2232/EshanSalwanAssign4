package eshan.salwan.n01422232;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas) {
        float w, h, cy, cx, radius;
        w = getWidth();
        h = getHeight();
        cy = h/2;
        cx = w/2;

        if(w > h) {
            radius = h/4;
        }else {
            radius = w/4;
        }

        Paint MyPaint = new Paint();
        MyPaint.setStyle(Paint.Style.FILL);

        int color1 = Color.BLUE;
        int color2 = Color.RED;
        int color3 = Color.GREEN;

        MyPaint.setAntiAlias(true);
        Shader linearGradiant;

        linearGradiant = new LinearGradient(
                cx, cy, cx+radius, cy+radius, color1, color3, Shader.TileMode.MIRROR);

        MyPaint.setShader(linearGradiant);
        canvas.drawCircle(cx, cy, radius, MyPaint);

    }
}
