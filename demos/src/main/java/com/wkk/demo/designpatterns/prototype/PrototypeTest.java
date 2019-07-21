package com.wkk.demo.designpatterns.prototype;

import java.io.IOException;

/**
 * @Description 原型模式
 * @Author wangkunkun
 * @Date 2018/07/01 21:09
 **/
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Work work = new Work();
        work.setName("worker");
        work.setWhere("China");
        Person person = new Person();
        person.setAge(18);
        person.setName("Test");
        person.setWork(work);
        Person clone = (Person) person.clone();
        System.out.println(clone);
        work.setWhere("shanghai");
        person.setName("haha");
        System.out.println(clone);
        Object deepClass = person.deepClass();
        work.setWhere("anhui");
        person.setName("xixi");
        System.out.println(deepClass);
    }
}
