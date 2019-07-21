package com.wkk.demo.designpatterns.observer;

import com.wkk.demo.designpatterns.observer.inter.Observer;

/**
 * @Description 观察者one
 * @Author wangkunkun
 * @Date 2018/07/07 11:08
 **/
public class ObserverOneImpl implements Observer {

    public void update() {
        System.out.println("update one------------------");
    }
}
