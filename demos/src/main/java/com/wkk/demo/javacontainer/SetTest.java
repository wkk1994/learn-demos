package com.wkk.demo.javacontainer;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * @Description Set测试
 * @Author wkk
 * @Date 2019-03-17 19:49
 **/
public class SetTest {

    public static void main(String[] args) {
        treeSetTest();
        hashSetTest();
        linkedHashSet();
    }

    private static void treeSetTest() {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("a");
        treeSet.add("c");
        treeSet.add("b");
        treeSet.add("a");
        //treeSet.add(null);//java.lang.NullPointerException
        for (String s : treeSet) {
            System.out.println("treeSet: "+s);
        }
    }

    private static void hashSetTest(){
        HashSet<String> hashSet = new HashSet();
        hashSet.add("a");
        hashSet.add("c");
        hashSet.add("b");
        hashSet.add("a");
        hashSet.add(null);
        for (String s : hashSet) {
            System.out.println("hashSet: "+s);
        }
    }

    private static void linkedHashSet(){
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("a");
        linkedHashSet.add("c");
        linkedHashSet.add("b");
        linkedHashSet.add("a");
        linkedHashSet.add(null);
        for (String s : linkedHashSet) {
            System.out.println("linkedHashSet: "+s);
        }
    }
}
