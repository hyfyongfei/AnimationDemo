package com.anima.yongfei.animationdemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.anima.yongfei.animationdemo.R;
import com.anima.yongfei.animationdemo.entity.PointEntity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yongfei on 16/12/29.
 */

public class TotalSnowView extends View{

    private Paint mPaint;
    private int mWindowWidth;
    private int mWindowHeight;
    private int mCount;
    private ArrayList<PointEntity> mPointEntities;
    private int[] mImages;

    public TotalSnowView(Context context) {
        super(context);
        init();
    }

    public TotalSnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TotalSnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TotalSnowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#890412"));
        // 初始化礼物数量🎁
        mCount = 10;
        mPointEntities = new ArrayList<>();
        mImages = new int[]{R.drawable.emoji_19,R.drawable.emoji_20,R.drawable.emoji_21,R.drawable.emoji_22,R.drawable.emoji_23, R.drawable.emoji_24
        ,R.drawable.emoji_25,R.drawable.emoji_26,R.drawable.emoji_27,R.drawable.emoji_28};

        // 获取屏幕宽高
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mWindowWidth = windowManager.getDefaultDisplay().getWidth();
        mWindowHeight = windowManager.getDefaultDisplay().getHeight();
//        1440===============2560

        setPointList();
    }

    // 获取随机的坐标位置
    private void setPointList() {
        PointEntity pointEntity;
        int x;
        int y;
        for (int i = 0; i < mCount; i++) {
            pointEntity = new PointEntity();
            x = new Random().nextInt(mWindowWidth);
            y = new Random().nextInt(mWindowHeight);
            pointEntity.x = x;
            pointEntity.y = y > mWindowWidth/2 ? y - mWindowWidth/2: y;
            mPointEntities.add(pointEntity);
        }
    }

    private Bitmap getImageResouce(int position){
        return ((BitmapDrawable) getResources().getDrawable(mImages[position])).getBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int positionX;
        int positionY;
        for (int i = 0; i < mCount; i++) {
            positionY = mPointEntities.get(i).y;
            positionX = mPointEntities.get(i).x - (positionY % 15 - positionY % 30);
            if (positionX > mWindowWidth){
                positionX -= mWindowWidth;
            } else if (positionX < 0){
                positionX = -positionX;
            }
            mPointEntities.get(i).x = positionX;

            positionY += 30;
            if (positionY >= mWindowHeight){
                positionY -= mWindowHeight;
            } else if (positionY < 0){
                positionY = 0;
            }
            Log.i("========","positionx:"+positionX+ "====== =positionY"+ positionY);
            mPointEntities.get(i).y = positionY;
            canvas.drawBitmap(getImageResouce(i), (float)positionX, (float)positionY,mPaint);
        }

        postInvalidateDelayed(50);
    }

}
