package com.wkk.demo.designpatterns.observer;

/**
 * @Description
 * @Author onepiece
 * @Date 2018/07/07 11:23
 **/
public class MySubject extends AbstractSubject {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        operation();
    }

    public void operation() {
        System.out.println("update state!");
        notifyObservers();
    }
}
