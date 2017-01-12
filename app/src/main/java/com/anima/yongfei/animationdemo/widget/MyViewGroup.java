package com.anima.yongfei.animationdemo.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by yongfei on 16/11/15.
 *
 * viewGroup 事件分发流程：
 *    this.dispatchTouchEvent >> this.onInterceptTouchEvent >> 子view.dispatchTouchEvent >> 子view.onTouchEvent
 *   首先判断是否拦截该事件，如果不拦截，通过viewGroup 找到处理该事件的子view，则直接交给子view处理，自己的onTouchEvent不会被触发
 * 否则交给自己的ontouchevent处理
 *
 * 可以通过复写onInterceptTouchEvent(ev)方法，拦截子View的事件（即return true），把事件交给自己处理，则会执行自己对应的onTouchEvent方法
 *
 * 子view可以通过调用getParent().requestDisallowInterceptTouchEvent
 */

public class MyViewGroup extends LinearLayout {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // 是否向下分发事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    // 是否拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_MOVE){

            return true;
        }

        return super.onInterceptTouchEvent(ev);
    }

    // 是否处理事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("----","layout ontouch");
        return super.onTouchEvent(event);
    }

    // 是否允许拦截点击事件
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
