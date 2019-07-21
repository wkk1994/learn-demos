package com.wkk.demo.designpatterns.prototype;

import java.io.Serializable;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/07/01 21:06
 **/
public class Work implements Cloneable, Serializable{

    private String name;

    private String where;

    @Override
    public String toString() {
        return "Work{" +
                "name='" + name + '\'' +
                ", where='" + where + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("调用Work clone方法");
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
