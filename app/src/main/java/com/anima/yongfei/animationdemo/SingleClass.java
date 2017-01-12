package com.anima.yongfei.animationdemo;

import android.content.Context;

/**
 * Created by yongfei on 16/11/1.
 * 懒汉式加载，提高效率
 */

public class SingleClass {
    private static SingleClass mSingleClass;
    private Context mContext;

    private SingleClass(Context context){
        this.mContext = context;
    }

    /**
     * 该方法的优点是：
     *   懒加载
     *   线程安全
     *   只有第一次会发生阻塞，判断同步（或者多线程并发时也会发生）
     * @param context
     * @return
     */
    // 直接在方法上加同步，由于该方法是static的，所以同步相当于类的同步效率低
    public SingleClass getIntance(Context context){
        // 加同步
        if (mSingleClass == null){
            synchronized (SingleClass.class){
                if (mSingleClass == null){
                    mSingleClass = new SingleClass(context);
                }
            }
        }
        return mSingleClass;
    }


}
