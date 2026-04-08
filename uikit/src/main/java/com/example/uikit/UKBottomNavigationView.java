package com.example.uikit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UKBottomNavigationView extends BottomNavigationView {

    private Path mPath;
    private Paint mPaint;

    /** Радиус "горба" */
    private final int CURVE_CIRCLE_RADIUS = (int) (32 * getResources().getDisplayMetrics().density);

    private PointF mFirstCurveStartPoint = new PointF();
    private PointF mFirstCurveEndPoint = new PointF();
    private PointF mFirstCurveControlPoint1 = new PointF();
    private PointF mFirstCurveControlPoint2 = new PointF();

    private PointF mSecondCurveStartPoint = new PointF();
    private PointF mSecondCurveEndPoint = new PointF();
    private PointF mSecondCurveControlPoint1 = new PointF();
    private PointF mSecondCurveControlPoint2 = new PointF();
    public UKBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.parseColor("#FF4081")); // Ваш розовый цвет
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Расчет точек кривой Безье для создания плавного горба по центру
        int cx = w / 2;

        mFirstCurveStartPoint.set(cx - (CURVE_CIRCLE_RADIUS * 2), 0);
        mFirstCurveEndPoint.set(cx, -CURVE_CIRCLE_RADIUS);

        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS, 0);
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - CURVE_CIRCLE_RADIUS, mFirstCurveEndPoint.y);

        mSecondCurveStartPoint.set(cx, -CURVE_CIRCLE_RADIUS);
        mSecondCurveEndPoint.set(cx + (CURVE_CIRCLE_RADIUS * 2), 0);

        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + CURVE_CIRCLE_RADIUS, mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - CURVE_CIRCLE_RADIUS, 0);

        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y);

        mPath.cubicTo(mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y);

        mPath.cubicTo(mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y);

        mPath.lineTo(w, 0);
        mPath.lineTo(w, h);
        mPath.lineTo(0, h);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}