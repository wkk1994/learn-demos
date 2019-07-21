package com.wkk.demo.designpatterns.memento;

/**
 * @Description 保存对象信息类
 * @Author wangkunkun
 * @Date 2018/07/08 10:32
 **/
public class Memento {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento(String state) {
        this.state = state;
    }
}
