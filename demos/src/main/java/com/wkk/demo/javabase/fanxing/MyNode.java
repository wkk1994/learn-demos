package com.wkk.demo.javabase.fanxing;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wkk
 * @Date 2019-01-29 21:18
 **/
public class MyNode extends Node<Integer> {

    public MyNode(Integer data) {
        super(data);
    }

    @Override
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }

    public static void main(String[] args) {
        Node node = new MyNode(5);
        node.setData("Hello");
    }
    /*public static <E> void append(List<E> list) {
        E elem = new E();  // compile-time error
        list.add(elem);
    }*/

    public static void rtti(List<?> list) {
        if (list instanceof ArrayList<?>) {  // OK; instanceof requires a reifiable type
            // ...
        }
    }
}
