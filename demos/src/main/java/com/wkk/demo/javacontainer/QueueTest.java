package com.wkk.demo.javacontainer;

import java.util.PriorityQueue;

/**
 * @Description Queue测试
 * @Author wkk
 * @Date 2019-03-18 20:25
 **/
public class QueueTest {

    public static void main(String[] args) {
        priorityQueueTest();
    }

    private static void priorityQueueTest(){
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("a");
        priorityQueue.add("c");
        priorityQueue.add("b");
        priorityQueue.add("a");
        // priorityQueue.add(null); java.lang.NullPointerException
        priorityQueue.forEach(e-> System.out.println("priorityQueue: "+e));
    }
}
