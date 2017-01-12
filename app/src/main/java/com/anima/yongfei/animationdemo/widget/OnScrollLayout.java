package com.anima.yongfei.animationdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by yongfei on 16/11/10.
 * 调用view的scrollTo() 和scrollBy()适用于滑动view中的内容，而不是滑动view的位置
 * 调用view的scrollTo() 和scrollBy()是用来滑动view中的内容的位置，而不是滑动view的位置
 * offsetLeftAndRight
 *  offsetTopAndBottom
 *  滑动当前view的位置
 */

public class OnScrollLayout extends LinearLayout{

    private int mMRawX;
    private int mMRawY;
    private int mMDownViewX;
    private int mMDownViewY;
    private Scroller mScroller;

    public OnScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    /**
     * 这里处理的是该viewGroup的子view的触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 实时更新位置坐标
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation(); // Stops the animation.
                }

                // 记录View相对于初始位置的滚动坐标。
                mMDownViewX = getScrollX();
                mMDownViewY = getScrollY();
                Log.i("-------", "mMDownViewX:"+mMDownViewX);
                Log.i("-------", "mMDownViewY:"+mMDownViewY);
                Log.i("-------", "x  :"+x);
                Log.i("-------", "y  :"+y);

                // 记录按下时的位置
                mMRawX = x;
                mMRawY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                // 相对于上次移动的移动距离
                int dX = x - mMRawX;
                int dy = y - mMRawY;
                // 更新上次移动位置
                mMRawX = x;
                mMRawY = y;
                // 移动
                scrollBy(-dX,-dy);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 返回初始位置 原始view的相对位置
                // TODO: 16/11/10 平滑移动
//                scrollTo(mMDownViewX, mMDownViewY);
//                getScrollX（）
                Log.i("-------", "target x  :"+getScrollX());
                Log.i("-------", "target y  :"+getScrollY());
                mScroller.startScroll(getScrollX(),getScrollY(),-getScrollX(),-getScrollY(),2000);
                invalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
//            Log.i("-------", "mScroller.getCurrX() x  :"+mScroller.getCurrX());  通过计算得到的x平移位置
//            Log.i("-------", "mScroller.getCurrY() y  :"+mScroller.getCurrY());  计算后得到的y平移位置
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }

    }
}
