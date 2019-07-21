package com.wkk.demo.designpatterns.memento;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/07/08 10:33
 **/
public class Originator {

    private String state;

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento){
        state = Memento.getState();
    }
}
