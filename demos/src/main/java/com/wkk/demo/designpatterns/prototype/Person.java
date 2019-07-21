package com.wkk.demo.designpatterns.prototype;

import java.io.*;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/07/01 21:04
 **/
public class Person implements Cloneable, Serializable{

    private String name;

    private Integer age;

    private Work work;

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //浅复制
        System.out.println("调用Person clone方法");
        //return super.clone();
        //深复制 TODO 这个方式实现不了 原来对象引用的对象也变了
        this.setWork((Work) this.work.clone());
        return super.clone();
    }


    public Object deepClass() throws IOException, ClassNotFoundException {
        //写入对象
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(outputStream);
        objectOutput.writeObject(this);
        //读取对象
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInput objectInput = new ObjectInputStream(inputStream);
        return objectInput.readObject();
    }
    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", work=" + work +
                '}';
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
