package com.anima.yongfei.animationdemo.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.anima.yongfei.animationdemo.entity.Point;

/**
 * Created by yongfei on 16/11/17.
 * Interpolator  控制动画变化的速率
 * TypeEvaluate  控制动画系统的属性从开始到结束的变化规则
 */

public class ObjectAnimatorView extends View{

    private Paint mPaint;
    private float RADIUS = 100;
    private Point mCurrentPoint;

    public ObjectAnimatorView(Context context) {
        super(context);
        init();
    }

    public ObjectAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ObjectAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(0xFFcccc99);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (mCurrentPoint == null){
//            drawCircle(canvas, mCurrentPoint);
//
//            startAnimate();
//        }else {
//
//            drawCircle(canvas, mCurrentPoint);
//        }
        if (mCurrentPoint == null){
            mCurrentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
        }else {
            drawCircle(canvas);

        }
    }

    public void startAnimate() {
        Point pointStart = new Point(RADIUS ,RADIUS);
        Point pointEnd = new Point((getWidth() - RADIUS)/2,(getHeight() - RADIUS)/2);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyEvaluator(), pointStart, pointEnd);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"rotation",0,360f);
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(this,"scaleX",1f,2f,1f);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(this,"scaleY",1f,0f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).with(objectAnimator).with(objectAnimatorX).with(objectAnimatorY);
        animatorSet.setDuration(5000);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.start();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle( mCurrentPoint.x, mCurrentPoint.y, RADIUS, mPaint);
    }

    private class MyEvaluator implements TypeEvaluator{

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            float x = startPoint.x + fraction * (endPoint.x - startPoint.x);
            float y = startPoint.y + fraction * (endPoint.y - startPoint.y);

            Point newPoint = new Point(x, y);
            return newPoint;
        }
    }

}
