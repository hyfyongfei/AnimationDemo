package com.anima.yongfei.animationdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by yongfei on 16/12/22.
 */

public class ShaderTextView extends TextView {

    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatix;
    private int mTranslate;
    private int mViewWidth;
    private Paint mNewPaint;

    public ShaderTextView(Context context) {
        super(context);

        init();
    }

    public ShaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public ShaderTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        myLog("  init     ");
        // 获取textview的画笔
        mPaint = getPaint();

        mTranslate = 10;

        mNewPaint = new Paint();
        mNewPaint.setColor(Color.BLUE);
        mNewPaint.setAlpha(50);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        myLog("  onMeasure     " + "widthMeasureSpec:" + getWidth() + "    heightMeasureSpec:" + getHeight());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        myLog("  onMeasure     " + "widthMeasureSpec:" + getWidth() + "    heightMeasureSpec:" + getHeight());

        /** Create a shader that draws a linear gradient along a line.
         @param x0           The x-coordinate for the start of the gradient line  渲染器梯度起始的x坐标
         @param y0           The y-coordinate for the start of the gradient line
         @param x1           The x-coordinate for the end of the gradient line
         @param y1           The y-coordinate for the end of the gradient line
         @param  colors      The colors to be distributed along the gradient line
         @param  positions   May be null. The relative positions [0..1] of
         each corresponding color in the colors array. If this is null,
         the the colors are distributed evenly along the gradient line.
         @param  tile        The Shader tiling mode
         */

        mViewWidth = getWidth();
        mLinearGradient = new LinearGradient(0, 0, mViewWidth , getHeight() ,
                new int[]{Color.BLACK, 0xffffffff, Color.BLACK}, null, Shader.TileMode.MIRROR);
        mPaint.setShader(mLinearGradient); // 设置渲染器

        mGradientMatix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.save();
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mNewPaint);
//        canvas.translate(50,0);

        myLog("  onDraw     ");
        super.onDraw(canvas);
//        canvas.restore();

        if (mGradientMatix != null) {
            if (mTranslate > mViewWidth * 3){
                mTranslate = mViewWidth;
            } else {
                mTranslate += mViewWidth / 10;
            }

//        将渲染器平移
            mGradientMatix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatix);
            // 300 毫秒后刷新界面
            postInvalidateDelayed(300);

        }
    }

    private void myLog(Object content) {
        Log.i("------", " 这是 log" + content);
    }

}
