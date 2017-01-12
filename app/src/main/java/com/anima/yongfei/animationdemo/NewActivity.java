package com.anima.yongfei.animationdemo;import android.animation.AnimatorSet;import android.app.Activity;import android.os.Bundle;import android.view.View;import android.widget.Button;import android.widget.TextView;import com.anima.yongfei.animationdemo.widget.ObjectAnimatorView;/** * Created by yongfei on 16/11/15. * * TypeEvaluator 计算动画从开始到结束值之间的过度    就是告诉动画系统如何从初始值过度到结束值 */public class NewActivity extends Activity {    private TextView mAnim;    private AnimatorSet mSet;    private ObjectAnimatorView mObjectAnimator;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_new);        mObjectAnimator = (ObjectAnimatorView) findViewById(R.id.animation_circle);        Button btn = (Button) findViewById(R.id.btn_anim);        Button btnPopup = (Button) findViewById(R.id.btn_show_popup);        btn.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                mObjectAnimator.startAnimate();//                ObjectAnimator moveIn = ObjectAnimator.ofFloat(mAnim,"translationX",-500,0);//                ObjectAnimator rotate = ObjectAnimator.ofFloat(mAnim,"rotation",0,720);//                ObjectAnimator fade = ObjectAnimator.ofFloat(mAnim,"alpha",1,0,1,0.5f);//                mSet = new AnimatorSet();//                mSet.addListener(new AnimatorListenerAdapter() {//                    @Override//                    public void onAnimationCancel(Animator animation) {//                        super.onAnimationCancel(animation);////                    }//                });//                mSet.play(rotate).with(fade).after(moveIn);//                mSet.setDuration(2000);//                mSet.start();//////                setFloatAnim();            }        });        btnPopup.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                // 弹窗点击事件            }        });    }    @Override    protected void onPause() {        super.onPause();    }    @Override    protected void onStop() {        super.onStop();        if (mSet != null && mSet.isRunning()){            mSet.cancel();        }    }}