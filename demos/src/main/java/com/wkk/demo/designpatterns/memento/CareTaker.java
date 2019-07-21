package com.wkk.demo.designpatterns.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 保存对象管理类
 * @Author wangkunkun
 * @Date 2018/07/08 10:37
 **/
public class CareTaker {

    private List<Memento> mementolist = new ArrayList<Memento>();

    public void add(Memento memento){
        mementolist.add(memento);
    }

    public Memento get(Integer index){
        return mementolist.get(index);
    }
}
