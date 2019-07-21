package com.wkk.demo.designpatterns.observer;

import com.wkk.demo.designpatterns.observer.inter.Observer;
import com.wkk.demo.designpatterns.observer.inter.Subject;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Description 被观察者抽象类
 * @Author wangkunkun
 * @Date 2018/07/07 11:12
 **/
public abstract class AbstractSubject implements Subject{

    private Vector<Observer> vector = new Vector<Observer>();

    public void add(Observer observer) {
        vector.add(observer);
    }

    public void del(Observer observer) {
        vector.remove(observer);
    }

    public void notifyObservers() {
        Enumeration<Observer> elements = vector.elements();
        while (elements.hasMoreElements()){
            Observer observer = elements.nextElement();
            observer.update();
        }
    }
}
