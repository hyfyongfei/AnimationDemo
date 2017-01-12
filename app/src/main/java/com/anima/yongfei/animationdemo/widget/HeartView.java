package com.anima.yongfei.animationdemo.widget;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.anima.yongfei.animationdemo.R;

import java.util.Random;

/**
 * Created by yongfei on 17/1/11.
 */

public class HeartView extends RelativeLayout{

    private LayoutParams mLayoutParams;

    public HeartView(Context context) {
        super(context);
        init();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addHeartView();
            }
        });
    }

    private void addHeartView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.heart_iamge, this, false);
        if (mLayoutParams == null){
            mLayoutParams = new LayoutParams(70,70);
            mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams.setMargins(10,10,10,10);
        }
        this.addView(view, mLayoutParams);
        startAnim(view);
    }

    protected void startAnim(final View view) {
        int width = getWidth();
        int height = getHeight();
        PointF pointstart = new PointF(width/2, height);
        PointF pointend = new PointF((width - (new Random()).nextInt(width))/2, 0);

//        AnimatorSet set = new AnimatorSet();
//        ObjectAnimator tranlateXAnim = ObjectAnimator.ofInt(view, "translationX", new BezierEvaluator(), );
//        ObjectAnimator tranlateYAnim = ObjectAnimator.ofInt(view, "translationY", );
//        ObjectAnimator alphaAnim = ObjectAnimator.ofInt(view, "alpha", );
//        set.setDuration(4000);
//        set.playTogether();

        ValueAnimator animator = ValueAnimator.ofObject(new BezierEvaluator(pointstart, pointend), pointstart, pointend);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);
                view.setY(point.y);
                //设置透明度
                view.setAlpha((1 - animation.getAnimatedFraction()) * 2);
            }
        });
        animator.setDuration(4000);
        animator.start();

    }

    /**
     * 三阶贝塞尔曲线计算公式
     * B(t)=(P0)(1-t)^3+3(P1)t(1-t)^2+3(P2)t^2(1-t)+(P3)t^3  (0≤t≤1)
     *
     * @link TypeEvaluator和Interpolator教程：http://www.cnblogs.com/wondertwo/p/5327586.html
     */
    private class BezierEvaluator implements TypeEvaluator<PointF> {
        private PointF pointF1;
        private PointF pointF2;
        public BezierEvaluator(PointF pointF1, PointF pointF2) {
            this.pointF1 = pointF1;
            this.pointF2 = pointF2;
        }
        @Override
        public PointF evaluate(float time, PointF startValue,
                               PointF endValue) {
            float timeLeft = 1.0f - time;
            PointF point = new PointF();//结果
            point.x = timeLeft * timeLeft * timeLeft * (startValue.x)
                    + 3 * timeLeft * timeLeft * time * (pointF1.x)
                    + 3 * timeLeft * time * time * (pointF2.x)
                    + time * time * time * (endValue.x);
            point.y = timeLeft * timeLeft * timeLeft * (startValue.y)
                    + 3 * timeLeft * timeLeft * time * (pointF1.y)
                    + 3 * timeLeft * time * time * (pointF2.y)
                    + time * time * time * (endValue.y);
            return point;
        }
    }
}
