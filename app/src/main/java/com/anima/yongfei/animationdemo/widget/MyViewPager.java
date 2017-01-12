package com.anima.yongfei.animationdemo.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by yongfei on 16/11/10.
 */

public class MyViewPager extends ViewGroup {

    private int mChildViewCount;
    private int mOldX;
    private int mOldY;
    private Scroller mScroller;
    private int mOldScroll;

    public MyViewPager(Context context) {
        this(context,null);
        Log.e("---------", "11111111");
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        Log.e("---------", "222");
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("---------", "3333");
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e("---------", "4444");
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mChildViewCount = getChildCount();
        Log.d("---------", "widthMeasureSpec :" + widthMeasureSpec + "-------" + "heightMeasureSpec :" + heightMeasureSpec);
        // 测量
        View childView;
        for (int i = 0; i < mChildViewCount; i++) {
            childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec); // 测量viewGroup中的子view
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            for (int i = 0; i < mChildViewCount; i++) {
                View childView = getChildAt(i);
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();
                childView.layout(i * childWidth, 0, (i + 1) * childWidth, childHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mOldX = x;
                mOldY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                // 移动位移
                int scrollX = x - mOldX;
                int scrollY = y - mOldY;

                mOldX = x;
                mOldY = y;
                scrollBy(-scrollX, 0);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 判断当前应该滑动到的位置
                Log.v("---------", "getWidth :" + getWidth() + "-------" + "getMeasuredWidth :" + getMeasuredWidth());
                int getScrollX = getScrollX();
                int scrollToView;
                if (getScrollX > mOldScroll){
                    scrollToView = (getScrollX + getWidth()*4/5) / getWidth();
                }else {
                    scrollToView = (getScrollX ) / getWidth();
                }
                Log.e("---","getScrollX"+getScrollX+"---"+"mOldScroll"+mOldScroll);
                mOldScroll = getScrollX;

                if (scrollToView >= mChildViewCount){
                    scrollToView = mChildViewCount - 1;
                }

                int dX = scrollToView * getWidth()-getScrollX;
                mScroller.startScroll(getScrollX, 0, dX, 0, 1000); // 初始化滑动开始的条件
                invalidate();
                return true;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        //Call this when you want to know the new location.  If it returns true, the animation is not yet finished.
        if (mScroller.computeScrollOffset()){
            // 递归
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            // 当根据初始值计算出下一次滑动的位置后 滑动到目标位置并刷新界面，再次计算下一个位置
            invalidate();
        }
    }
}
