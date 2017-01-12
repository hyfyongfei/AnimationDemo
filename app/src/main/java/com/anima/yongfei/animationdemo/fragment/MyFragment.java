package com.anima.yongfei.animationdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anima.yongfei.animationdemo.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by yongfei on 16/11/4.
 */

public class MyFragment extends Fragment {

//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            if (msg.what == 111){
//                mTv.setText((String)msg.obj);
//            }
//        }
//    };

    private Message mMessage;
    private TextView mTv;
    private Button mHandBtn;
    private int mCountTime = 0;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1111){
                mCountTime ++;
                if (mCountTime <= 10){
                    testTimer();// 递归
                    mTv.setText(String.valueOf(mCountTime));
                }
            }

        }
    };

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("asddddddddddd");
                    mCountTime ++;
                    if (mCountTime < 10){
                        mTv.setText("`````"+String.valueOf(mCountTime));

                    }else {
                        mTimer.cancel();
                    }
                }
            });
        }
    };

    private Timer mTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my ,container,false);
        mTv = (TextView) view.findViewById(R.id.tv_fragment_content);
        mHandBtn = (Button) view.findViewById(R.id.handler_btn);
        mTv.setText("第一个 fragment 哈哈哈哈哈哈哈");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTimer = new Timer();

        mHandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                countTimer();
//                testTimer();
                testHandlerDelay();
            }
        });
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    private void countTimer(){
        mMessage = Message.obtain();
        mMessage.what = 111;
        mMessage.obj = "fffffffff";
        mHandler.sendMessage(mMessage);
    }

    private void testTimer(){
        mHandler.sendEmptyMessageDelayed(1111,1000);

        ////////
//        mHandler.postDelayed();
    }

    private void testHandlerDelay(){
        mTimer.schedule(timerTask, 0,1111);

    }

}
