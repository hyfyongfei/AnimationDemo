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

public class FlashTextView extends TextView{

    private Paint mPaint1;
    private Paint mPaint2;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;
    private int mViewWidth;
    private Paint mPaint;

    public FlashTextView(Context context) {
        super(context);
        init();
    }

    public FlashTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlashTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FlashTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mTranslate = 5;

        //初始化用来绘制背景边框的笔
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint1.setColor(Color.RED);
        mPaint2.setColor(Color.GRAY);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //闪动效果的对象初始化工作
        if(mViewWidth==0){
            mViewWidth=getMeasuredWidth();
            if(mViewWidth>0){
                //通过getPaint()方法获取绘制TextView的画笔
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,
                        new int[]{Color.BLACK,0xffffffff,Color.BLACK},
                        null, Shader.TileMode.CLAMP);
                //将该属性赋予给paint对象的shader渲染器
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    /**
     * 这里canvas.save();和canvas.restore();是两个相互匹配出现的，作用是用来保存画布的状态和取出保存的状态的。当我们对画布进行旋转，缩放，
     * 平移等操作的时候其实我们是想对特定的元素进行操作，比如图片，一个矩形等，但是当你用canvas的方法来进行这些操作的时候，其实是对整个画布进
     * 行了操作，那么之后在画布上的元素都会受到影响，所以我们在操作之前调用canvas.save()来保存画布当前的状态，当操作之后取出之前保存过的状态，
     * 这样就不会对其他的元素进行影响
     *
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.translate(50,0);

        // 首先保存当前canvas的状态，再对画布进行缩放操作后重新取出之前保存的状态，这时 下一次画布的状态就不会因为之前的操作而发生变化，（在对画布做一些操作的时候其实是对画
        // 布本身做的一些操作，如果在进行一些操作之后不恢复之前的状态下次再次使用canvas时候就会受到出之前的对画布做的操作的影响）
        canvas.save();

        canvas.scale(0.3f,0.5f);

        //绘制字体之前
        //我们在这里绘制外矩形
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        //绘制内矩形
        canvas.drawRect(5,5,getMeasuredWidth()-5,getMeasuredHeight()-5,mPaint2);
        //绘制字体之前向右平移5像素

        canvas.restore();

        //开始绘制字体
        super.onDraw(canvas);

        //绘制字体之后
//        canvas.restore();
        if (mGradientMatrix !=null){
            // 计算每次平移的值
            mTranslate += mViewWidth / 5;
            if(mTranslate >2*mViewWidth){
                mTranslate =-mViewWidth;
            }

            // 每隔一段时间都重新绘制界面重新赋值
            mGradientMatrix.setTranslate(mTranslate,0);
            // 重新设置渲染器
            mLinearGradient.setLocalMatrix(mGradientMatrix);

            // 延时100毫秒引起界面重新绘制
            postInvalidateDelayed(300);
        }

    }

    private void showLog(Object content){
        Log.i("--------", "====   :"+content);
    }
}
