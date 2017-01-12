package com.anima.yongfei.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.anima.yongfei.animationdemo.widget.MyTextView;

/**
 * Created by yongfei on 17/1/11.
 */

public class TranslateActivity extends Activity {

    private MyTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_translate);

        mTextView = (MyTextView) findViewById(R.id.tv_my_change);

        findViewById(R.id.btn_start_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnimation();
            }
        });
    }

    // "" 代表的是字符串 ''代表的是字符
    private void startAnimation() {
        PropertyValuesHolder textValuesHolder = PropertyValuesHolder.ofObject("CharText",new ChatTypeEvaluator(), new Character('A'), new Character('Z'));
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, textValuesHolder);
        animator.setInterpolator(new LinearInterpolator());

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mTextView, "translationX", 0f, 300f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator, animator1);
        set.setDuration(4000);
        set.start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
    }


    private class ChatTypeEvaluator implements TypeEvaluator<Character> {

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            Log.i(" ===" ,"aaa" + startValue + endValue);
            int startChat = (int) startValue;
            int endChat = (int) endValue;
            int currentChat = (int) (startChat + fraction * (endChat - startChat));
            char result = (char)(currentChat);
            return result;
        }
    }
}
