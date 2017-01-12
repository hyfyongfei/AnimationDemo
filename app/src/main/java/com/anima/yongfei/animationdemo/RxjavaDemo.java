package com.anima.yongfei.animationdemo;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class RxjavaDemo {

    public void rxTry(){
        // 发射一个空的Observable
        Observable.empty().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });

        //   创建在一个指定的延迟之后发射单个数据的Observable  延时时间\时间单位
        Observable observable = Observable.timer(2, TimeUnit.SECONDS);
        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("-------------------");
            }
        });
    }
}
