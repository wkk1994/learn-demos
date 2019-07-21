package com.wkk.demo.javacontainer;

import com.wkk.demo.references.entity.Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description Map测试
 * @Author wkk
 * @Date 2019-03-18 20:38
 **/
public class MapTest {

    public static void main(String[] args) {
        treeMapTest();
        hashMapTest();
        linkedHashMap();
        hashTable();
        concurrentHashMap();
        weakHashMap();
    }

    private static void treeMapTest(){
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.put("a","a");
        treeMap.put("c","c");
        treeMap.put("b","b");
        treeMap.put("a","a111");
        //treeMap.put(null,"a");  java.lang.NullPointerException
        treeMap.put("null",null);
        treeMap.put("null11",null);
        treeMap.forEach((key,value)-> System.out.println("treeMap "+key+" : "+value));
    }

    private static void hashMapTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("a","a");
        hashMap.put("c","c");
        hashMap.put("b","b");
        hashMap.put("a","a111");
        hashMap.put(null,"a");
        hashMap.put("null",null);
        hashMap.put(null,null);
        hashMap.forEach((key,value)-> System.out.println("hashMap "+key+" : "+value));
    }

    private static void linkedHashMap(){
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("a","a");
        linkedHashMap.put("c","c");
        linkedHashMap.put("b","b");
        linkedHashMap.put("a","a11");
        linkedHashMap.put(null,"null11");
        linkedHashMap.put(null,null);
        linkedHashMap.forEach((key,value)-> System.out.println("linkedHashMap "+key+" : "+value));
    }

    private static void hashTable(){
        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("a","a");
        hashtable.put("c","c");
        hashtable.put("b","b");
        hashtable.put("a","a11");
        //hashtable.put(null,"a11"); //java.lang.NullPointerException
        //hashtable.put("a",null);//java.lang.NullPointerException
        hashtable.forEach((key,value)-> System.out.println("Hashtable "+key+" : "+value));
    }

    private static void concurrentHashMap(){
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("a","a");
        concurrentHashMap.put("c","c");
        concurrentHashMap.put("b","b");
        concurrentHashMap.put("a","a11");
        //concurrentHashMap.put(null,"a11");//java.lang.NullPointerException
        //concurrentHashMap.put("a",null);
        concurrentHashMap.forEach((key,value)-> System.out.println("concurrentHashMap "+key+" : "+value));
    }

    private static void weakHashMap(){
        Entity test3 = new Entity(1003, "test3");
        WeakHashMap<Entity,String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(new Entity(1001, "test1"),"test1");
        weakHashMap.put(new Entity(1002, "test2"),"test2");
        weakHashMap.put(test3,"test3");
        System.gc();
        weakHashMap.forEach((key,value)-> System.out.println(" weakHashMap "+key+" : "+value));
    }
}
