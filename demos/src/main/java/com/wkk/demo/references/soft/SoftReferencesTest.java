package com.wkk.demo.references.soft;

import com.wkk.demo.references.entity.Entity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @Description 软引用示例代码
 * @Author wkk
 * @Date 2018/06/10 20:44
 **/
public class SoftReferencesTest {
    public static void main(String[] args) {
        softQueue();
    }

    public static void soft(){
        //强引用
        Entity entity = new Entity(1001,"Test");
        //创建一个软引用指向这个对象   那么此时就有两个引用指向Entity对象
        SoftReference<Entity> softReference = new SoftReference<Entity>(entity);
        entity = null;
        Entity[] es = new Entity[500];
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("softentity:"+softReference.get());
    }


    public static void softQueue() {
        //强引用
        Entity entity = new Entity(1001,"Test");
        //创建一个软引用指向这个对象   那么此时就有两个引用指向Entity对象
        ReferenceQueue queue = new ReferenceQueue();
        SoftReference<Entity> softReference = new SoftReference<Entity>(entity,queue);
        entity = null;
        while (queue.poll() != null) {
            // 清除ref
            System.out.println("dfg:"+queue.poll());
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("queue:"+queue.poll());
        System.out.println("softentity:"+softReference.get());
    }
}
