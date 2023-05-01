package com.example.spiritlevel;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Point;
import androidx.annotation.Nullable;

public class SpiritLevelView extends View {

    Point center = new Point();
    float width;
    float height;
    Point bubble = new Point();
    static final int BUBBLE_SIZE = 50;
    Paint paint = new Paint();
    float x;
    float y;

    public SpiritLevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.x = w / 2;
        center.y = h / 2;
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(bubble.x, bubble.y, BUBBLE_SIZE, paint);
    }

    public void setBubble(float x, float y) {
        bubble.x = (int) (center.x + x / 9.81f * width);
        bubble.y = (int) (center.y + y / 9.81f * height);
        Log.i("SpiritLevelView", bubble.toString());

        invalidate();
    }
}
