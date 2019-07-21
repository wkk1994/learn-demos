package com.wkk.demo.designpatterns.observer;

import com.wkk.demo.designpatterns.observer.inter.Observer;
import com.wkk.demo.designpatterns.observer.inter.Subject;

/**
 * @Description 测试类
 * @Author wangkunkun
 * @Date 2018/07/07 11:24
 **/
public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new MySubject();
        Observer observerOne = new ObserverOneImpl();
        Observer observerTwo = new ObserverTwoImpl();
        subject.add(observerOne);
        subject.add(observerTwo);
        subject.operation();
    }
}
