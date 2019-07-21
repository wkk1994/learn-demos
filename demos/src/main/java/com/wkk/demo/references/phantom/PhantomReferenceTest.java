package com.wkk.demo.references.phantom;

import com.wkk.demo.references.entity.Entity;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @Description 虚引用示例代码
 * @Author onepiece
 * @Date 2018/06/11 21:58
 **/
public class PhantomReferenceTest {

    public static void main(String[] args) {
        phantom();
    }

    public static void phantom(){
        //必须和队列同用
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<Entity> phantomReference = new PhantomReference<Entity>(new Entity(1001,"hello"),queue);
        System.out.println("entity: "+phantomReference.get());

        System.gc();
        System.out.println("entity: "+phantomReference.get());
        while (queue.poll()!= null){
            System.out.println("存在已失去引用对象wark"+queue.poll());
        }
    }
}
