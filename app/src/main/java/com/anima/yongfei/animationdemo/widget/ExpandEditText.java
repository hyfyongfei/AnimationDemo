package com.anima.yongfei.animationdemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.widget.Button;

/**
 * Created by yongfei on 16/11/15.
 * ~ 如果当前view的 setOnTouchListener() 被调用的话 view 本身的 onTouchListener()就不会被调用
 * ~ TouchDelegate 如果希望可以增加自己view的点击范围而不更改view的宽度可以使用
 *
 * ~~ view的事件的转发流程是
 *     view.dispatchTouchEvent >> View.setOnTouchListener (如果返回false)>> View.onTouchEvent()
 *     [view.dispatchTouchEvent 调用 isAccessibilityFocusedViewOrHost() 判断是否右焦点、或者是否有子view 如果 当前view
 *     没有焦点且无子view 返回 false ，交给该view的父view处理 否则自己onTouchEvent() 处理或者交给子view处理]
 *
 *
 *
 */

public class ExpandEditText extends Button {
    public ExpandEditText(Context context) {
        this(context, null);
    }

    public ExpandEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        setBackgroundColor(Color.parseColor("#ff0000"));
        Rect rect = new Rect();
        rect.set(100,100,100,100);
        // 更改view点击范围
        setTouchDelegate(new TouchDelegate(rect, this));
    }

    // 用于事件的分发 在没有子布局的view中不需要overWrite
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    // 处理view的触摸、点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("----","view  ontouch");
        return true;
//        return super.onTouchEvent(event);
    }
}
