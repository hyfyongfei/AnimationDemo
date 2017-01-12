package com.anima.yongfei.animationdemo.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 * Created by yongfei on 16/11/15.
 */

public class KeyBoardLinearLayout extends LinearLayout{

    private ViewTreeObserver.OnGlobalLayoutListener mMLayoutChangeListener;

    public KeyBoardLinearLayout(Context context) {
        super(context);
        init();
    }

    public KeyBoardLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyBoardLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KeyBoardLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mMLayoutChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() {
             @Override
             public void onGlobalLayout() {
                 // 判断窗口可见区域大小
                 Rect r = new Rect();
                 ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                 // 如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                 int screenHeight = ((Activity) getContext()).getWindow().getDecorView().getRootView().getHeight();
                 int heightDifference = screenHeight - (r.bottom - r.top);
                 boolean isKeyboardShowing = heightDifference > screenHeight / 3;

                 Log.i("-----","view soft keyboard status :"+isKeyboardShowing);
             }
         };

        // 注册布局变化监听
        ((Activity)getContext()).getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mMLayoutChangeListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        ((Activity)getContext()).getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mMLayoutChangeListener);
    }
}
