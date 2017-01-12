package com.anima.yongfei.animationdemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yongfei on 16/11/9.
 */

public class ReflactDemo {
    public int asd;
    private String asdf = "1111111";

    private void asdasdgetDemoprivate(Context context){
        Toast.makeText(context,"aaaaa"+asdf,Toast.LENGTH_LONG).show();
    }

    public void asdasdgetPublic(Context context){
        Toast.makeText(context,"ffffffff"+asdf,Toast.LENGTH_LONG).show();
    }


}
