package com.wkk.demo.references.waek;

import com.wkk.demo.references.entity.Entity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Description 弱引用示例代码
 * @Author onepiece
 * @Date 2018/06/11 21:21
 **/
public class WeakReferenceTest {

    public static void main(String[] args) {
        weakQueue();
    }

    public static void weak(){
        WeakReference<Entity>  weakReference = new WeakReference<Entity>(new Entity(1001,"test"));
        System.out.println("entity: "+weakReference.get());
        System.gc();//通知GC回收
        System.out.println("entity: "+weakReference.get());
    }

    public static void weakQueue(){
        ReferenceQueue<Entity> queue = new ReferenceQueue<Entity>();
        WeakReference<Entity>  weakReference = new WeakReference<Entity>(new Entity(1001,"test"),queue);
        System.out.println("entity: "+weakReference.get());
        System.gc();//通知GC回收
        System.out.println("entity: "+weakReference.get());
        while (queue.poll()!= null){
            System.out.println("存在已失去引用对象wark"+queue.poll());
        }
    }
}
