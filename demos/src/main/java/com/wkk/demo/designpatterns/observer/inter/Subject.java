package com.wkk.demo.designpatterns.observer.inter;

import com.wkk.demo.designpatterns.observer.inter.Observer;

/**
 * @Description 被观察者接口
 * @Author wangkunkun
 * @Date 2018/07/07 11:03
 **/
public interface Subject {

    /*增加观察者*/
    public void add(Observer observer);

    /*删除观察者*/
    public void del(Observer observer);

    /*通知所有的观察者*/
    public void notifyObservers();

    /*自身的操作*/
    public void operation();

}
