package com.anima.yongfei.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yongfei on 16/11/10.
 */

public class TouchActivity extends Activity implements View.OnClickListener{

    private Button mTouchBtn;
    private Button mTouchBtnto;
    private TextView mTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        mTouchBtn = (Button) findViewById(R.id.touch_btn);
        mTouchBtn.setOnClickListener(this);
        mTouchBtnto = (Button) findViewById(R.id.touch_btn_to);
        mTouchBtnto.setOnClickListener(this);
        mTouchView = (TextView) findViewById(R.id.touch_tv);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.touch_btn:
                int asd  = -50;
                mTouchView.scrollBy(asd,asd);
                break;
            case R.id.touch_btn_to:
                mTouchView.scrollTo(12,12);
                break;
        }

    }
}
