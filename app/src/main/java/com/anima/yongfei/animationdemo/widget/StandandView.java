package com.anima.yongfei.animationdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yongfei on 17/1/10.
 */

public class StandandView extends View {

    private Paint mPaint;
    private int mRadius;

    public StandandView(Context context) {
        this(context, null);
    }

    public StandandView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StandandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public StandandView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#00ff8f0f"));
        mPaint.setStyle(Paint.Style.STROKE);

        mRadius = 50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2, getHeight()/2, mRadius, mPaint);
    }
}
