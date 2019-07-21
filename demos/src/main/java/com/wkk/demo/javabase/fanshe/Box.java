package com.wkk.demo.javabase.fanshe;

import java.util.ArrayList;

/**
 * @Description 反射类
 * @Author wkk
 * @Date 2019-01-20 18:34
 **/
public class Box <T>{

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public <H> H comple(){
        return null;
    }

    public <H> String comple1(H h){
        return h.toString();
    }

    public static void main(String[] args) {
        Box<Integer> box = new Box<Integer>();
        Box<String> stringBox = new Box<String>();
        box.<String>comple();
        System.out.println(box.<String>comple1("test"));
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true
    }
}
