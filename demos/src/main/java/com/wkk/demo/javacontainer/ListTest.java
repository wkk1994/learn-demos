package com.wkk.demo.javacontainer;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @Description List测试
 * @Author wkk
 * @Date 2019-03-17 20:11
 **/
public class ListTest {

    public static void main(String[] args) throws Exception {
        arrayListTest();
        vectorTest();
        linkedListTest();
        arrayListWR();
    }

    private static void arrayListTest(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add(null);
        arrayList.forEach(e -> System.out.println("arrayList : "+e));

    }

    private static void vectorTest(){
        Vector<String> vector = new Vector<>();
        vector.add("a");
        vector.add("c");
        vector.add("b");
        vector.add("a");
        vector.add(null);
        vector.add(null);
        vector.forEach(e -> System.out.println("vector: "+e));

    }

    private static void linkedListTest(){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("c");
        linkedList.add("b");
        linkedList.add("a");
        linkedList.add(null);
        linkedList.add(null);
        linkedList.forEach(e -> System.out.println("linkedList: "+e));

    }

    private static void arrayListWR() throws Exception {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add(null);
        File file = new File("d:\\ss.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(arrayList);
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        ArrayList<String> arrayList1 = (ArrayList<String>) inputStream.readObject();
        System.out.println(arrayList1);
    }
}
