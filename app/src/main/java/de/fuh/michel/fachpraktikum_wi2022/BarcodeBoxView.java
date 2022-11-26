package de.fuh.michel.fachpraktikum_wi2022;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class BarcodeBoxView extends View {

    private Paint paint = new Paint();

    private RectF mRect = new RectF();

    public BarcodeBoxView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cornerRadius = 10f;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5f);

        if (canvas != null) {
            canvas.drawRoundRect(mRect, cornerRadius, cornerRadius, paint);
        }
    }

    public void setRect(RectF rect) {
        mRect = rect;
        invalidate();
        requestLayout();
    }

}