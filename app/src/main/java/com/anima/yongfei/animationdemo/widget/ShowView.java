package com.anima.yongfei.animationdemo.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.anima.yongfei.animationdemo.R;

/**
 * Created by yongfei on 16/11/8.
 */

public class ShowView extends LinearLayout{
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;

    public ShowView(Context context) {
        super(context);
        initView(context);
    }


    private void initView(Context context) {
        inflate(getContext(), R.layout.aasdasd,this);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mLayoutChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 判断窗口可见区域大小
                Rect r = new Rect();
                ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                // 如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int screenHeight = ((Activity) getContext()).getWindow().getDecorView().getRootView().getHeight();
                int heightDifference = screenHeight - (r.bottom - r.top);
                boolean isKeyboardShowing = heightDifference > screenHeight / 3;

            }
        };

        // 注册布局变化监听
        ((Activity) getContext()).getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mLayoutChangeListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();


        //移除布局变化监听
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ((Activity) getContext()).getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutChangeListener);
        } else {
            ((Activity) getContext()).getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(mLayoutChangeListener);
        }
    }


}
