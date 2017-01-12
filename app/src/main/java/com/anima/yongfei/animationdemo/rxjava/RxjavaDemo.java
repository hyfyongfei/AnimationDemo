package com.anima.yongfei.animationdemo.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by yongfei on 16/11/11.
 */

public class RxjavaDemo {

    private void rxDemo(){
        Observable observable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {

            }
        });

        // 订阅发生的线程（观察者发生的线程）
        observable.subscribeOn(Schedulers.io());
        // 发布事件发生的线程 （）
        observable.observeOn(Schedulers.computation());

        //Subscriber()是一个接口 向下转型成具体实现类 观察者
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        };
    }

}
