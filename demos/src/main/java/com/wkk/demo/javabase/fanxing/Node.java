package com.wkk.demo.javabase.fanxing;

/**
 * @Description
 * @Author wkk
 * @Date 2019-01-29 21:17
 **/
public class Node<T> {

    public  T data;

    public Node(T data) { this.data = data; }
    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }

}
