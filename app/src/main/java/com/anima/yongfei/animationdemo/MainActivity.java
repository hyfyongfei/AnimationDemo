package com.anima.yongfei.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anima.yongfei.animationdemo.fragment.MyFragment;
import com.anima.yongfei.animationdemo.fragment.MyFragmentB;
import com.anima.yongfei.animationdemo.fragment.MyFragmentC;
import com.anima.yongfei.animationdemo.widget.MyDialog;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView mTextView;
    private FrameLayout mContainer;
    private Button mButtonFragment;
    private Button newReflact;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startAnim = (Button) findViewById(R.id.btn_start_anim);
//        mTextView = (TextView) findViewById(R.id.demo_tv);
//        mTextView.setText(setne333wStyle("三大件宽松的南京卡爱上的那款就是的那款暗示的健康南京爱上动脑筋"));
//        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        startAnim.setOnClickListener(this);
//
//        mContainer = (FrameLayout) findViewById(R.id.container);
//
        mButtonFragment = (Button) findViewById(R.id.btn_fragment);
        mButtonFragment.setOnClickListener(this);

        newReflact = (Button) findViewById(R.id.btn_flact);
        newReflact.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.assssss);
//        linearLayout.addView(new ShowView(this));


        findViewById(R.id.btn_touch).setOnClickListener(this);

        findViewById(R.id.btn_notify).setOnClickListener(this);

        findViewById(R.id.btn_view_activity).setOnClickListener(this);
        findViewById(R.id.aaaaaaiodiaisdi).setOnClickListener(this);

        findViewById(R.id.btn_view_activity).setOnClickListener(this);

        findViewById(R.id.qwioeqioweijqiweiqew).setOnClickListener(this);

        findViewById(R.id.total_snow).setOnClickListener(this);
    }

    private SpannableString setMsgSpannableStyle(String hyperlinkText) {
        SpannableString spannable = new SpannableString(hyperlinkText);
        spannable.setSpan(new ClickableSpan() {

                              @Override
                              public void updateDrawState(TextPaint ds) {
                                  super.updateDrawState(ds);

                              }

                              @Override
                              public void onClick(View widget) {
                                  Toast.makeText(getBaseContext(), "------", Toast.LENGTH_LONG).show();
                              }
                          }, 0,
                3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    private SpannableString setnewStyle(String hyperlinkText) {
        SpannableString spannable = new SpannableString(hyperlinkText);
        spannable.setSpan(new ClickableSpan() {
                              @Override
                              public void onClick(View widget) {
                                  Toast.makeText(getBaseContext(), "------", Toast.LENGTH_LONG).show();
                              }

                              @Override
                              public void updateDrawState(TextPaint ds) {
                                  ds.setColor(Color.parseColor("#abc123"));
                                  ds.setUnderlineText(true);
                              }
                          }, 0,
                3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    private SpannableString setne333wStyle(String hyperlinkText) {
        SpannableString spannable = new SpannableString(hyperlinkText);
        spannable.setSpan(new URLSpan("admkalsd"), 0,
                3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    private void alphaAnimation(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 123.5f, 330f, 0.0f);
        anim.setDuration(2000);
        anim.start();
    }

    private void scaleAnimation(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 12.5f);
        anim.setDuration(2000);
        anim.start();
    }

    private void rotationAnimation(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotation", 1.0f, 12.5f);
        anim.setDuration(2000);
        anim.start();
    }

    private void translationAnimation(View view) {
        ObjectAnimator animx = ObjectAnimator.ofFloat(view, "translationX", 1.0f, 125f);
        ObjectAnimator animy = ObjectAnimator.ofFloat(view, "translationX", 1.0f, 125f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animx, animy);
        set.setDuration(2000);
        set.start();
    }

    private void showDialog() {
        new MyDialog.Builder(this).create().show();
    }

    /**
     * 通过Intent传递参数到目标Activity的Fragment中
     * <p>
     * Fragment f = new Fragment();
     * Bundle b = new Bundle();
     * b.putInt("",1);
     * f.setArgument(b);
     * <p>
     * getArgument();
     * <p>
     * <p>
     * fragment 和 activity之间的交互：
     * 1、activity中有fragment的引用时可以直接拿到fragment中的public的方法
     * 2、activity中没有fragment的引用时，可以通过每一个打开的fragment时的tag或者id找到这个fragment
     * 3、通过回调监听获取相互之间调用
     * <p>
     * fragment和fragment 之间交互
     * <p>
     * fragment 和 activity之间参数：
     * 1、通过Intent传递参数到目标Activity的Fragment中
     * 2、
     * <p>
     * FragmentPagerAdapter与FragmentStatePagerAdapter
     * FragmentPagerAdapter：对于不再需要的fragment，选择调用detach方法，仅销毁视图，并不会销毁fragment实例。
     * FragmentStatePagerAdapter：会销毁不再需要的fragment，当前事务提交以后，会彻底的将fragment从当前Activity的FragmentManager中移除，
     * state标明，销毁时，会将其onSaveInstanceState(Bundle outState)中的bundle信息保存下来，当用户切换回来，可以通过该bundle恢复生成新的fragment，
     * 也就是说，你可以在onSaveInstanceState(Bundle outState)方法中保存一些数据，在onCreate中进行恢复创建。
     */

    // fragment
    private void onStartFragment() {
        /**
         * public abstract FragmentTransaction add(Fragment fragment, String tag);
         * begin a empty fragment with no interface
         *
         * public abstract FragmentTransaction add(int containerViewId, Fragment fragment, String tag);
         * begin a fragment with tag, and we can find this fragment by tag;
         *
         *  transaction.addToBackStack("amksdasdlmaksd");
         *  添加到回退站
         *
         */
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("asd", "asd");
        myFragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container, myFragment, "myFragment");
        transaction.addToBackStack("amksdasdlmaksd");
        transaction.commit();
    }

    private void getFragmentViewPager() {
        MyFragment myFragmentA = new MyFragment();
        MyFragmentB myFragmentB = new MyFragmentB();
        MyFragmentC myFragmentC = new MyFragmentC();
        mListFragment = new ArrayList<>();
        mListFragment.add(myFragmentA);
        mListFragment.add(myFragmentB);
        mListFragment.add(myFragmentC);

        // 添加fragmentAdapter 指示器

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mListFragment.get(position);
            }

            @Override
            public int getCount() {
                return mListFragment.size();
            }
        };

        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);
    }


    /**
     * 最终在newActivity()里返回的是利用cl.loadClass返回的Activity对象。可知，Activity对象的创建是通过反射完成的。java程序可以动态加载类定义，而这个动态加载的机制就是通过ClassLoader来实现的，所以可想而知ClassLoader的重要性如何。
     * <p>
     * 获取一个Class的写法：
     * Class c = Class.forName("java.lang.String");  //这里一定要用完整的包名
     * <p>
     * Class c1=String.class;
     * <p>
     * String str = new String();
     * Class c2=str.getClass();
     * 一般在反射中使用第一种方法
     * <p>
     * 获取类的属性
     * Field[] fields = new c.getDeclaredFields();
     * <p>
     * 3.获取类的方法
     * // 获取所有的方法
     * Method[] ms = c.getDeclaredMethods();
     */
    private void loadMethod() {
        try {
            Class temp = Class.forName("com.anima.yongfei.animationdemo.ReflactDemo");
            System.out.println("反射类中所有的方法");
            Method[] fm = temp.getDeclaredMethods();
            for (int i = 0; i < fm.length; i++) {
                System.out.println("method name :" + fm[i].getName() + "-----return type :" + fm[i].getReturnType() + "____"
                        + fm[i].getReturnType().getName());
            }
            Method asd = temp.getDeclaredMethod("asdasdgetDemoprivate", Context.class);
            asd.setAccessible(true); // 调用类中私有方法时添加access权限
            asd.invoke(new ReflactDemo(), getBaseContext());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void loadTwo() {
//        Class.forName()
        try {
            Class test = ClassLoader.getSystemClassLoader().loadClass("com.anima.yongfei.animationdemo.ReflactDemo");
            // 获取构造方法
            Constructor constructor = test.getConstructor();
            // 通过构造获取实例化对象
            Object obj = constructor.newInstance();
            // 通过class调方法
            Method method = test.getMethod("method name", String.class);
            // private是否可用
            method.setAccessible(true);
            // the object on which to call this method (or null for static methods) , parameter
            method.invoke(new Object(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewTouchEvent() {
        Intent intent = new Intent(this,TouchActivity.class);
        intent.putExtra("",123123);
        setResult(12312);
        startActivity(intent);
    }

    private void notifyAction(){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        Notification notification = builder
                .setContentTitle("这是通知标题")
                .setContentText("这是通知内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.pop_ic_payment_management)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .build();
        manager.notify(1, notification);
    }

    private void animDemo(){
        newReflact.animate().alpha(0f);
    }


    private void newActivity() {
        Intent intent = new Intent(getBaseContext(),NewActivity.class);
        startActivity(intent);

    }

    private void newActivity2() {
        Intent intent = new Intent(getBaseContext(),TestActivity.class);
        startActivity(intent);

    }

    private void newActivity3() {
        Intent intent = new Intent(getBaseContext(),TotalSnowActivity.class);
        startActivity(intent);

    }

    private void startNewFragment(){
        Intent intent = new Intent(getBaseContext(),Android50activity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fragment: // fragment

//                onStartFragment();

                getFragmentViewPager();

                break;
            case R.id.btn_start_anim: // other
                new RxjavaDemo().rxTry();
                showDialog();
                break;

            case R.id.btn_flact:

                loadMethod();
                break;
            case R.id.btn_touch:
                viewTouchEvent();
                break;

            case R.id.btn_notify:
                notifyAction();
                animDemo();
                break;

            case R.id.btn_view_activity:
                newActivity();

                break;
            case R.id.aaaaaaiodiaisdi:
                startNewFragment();

                break;

            case R.id.qwioeqioweijqiweiqew:
                newActivity2();
                break;
            case R.id.total_snow:
                newActivity3();
                break;
        }
    }

}
