package com.wkk.demo.designpatterns.singleton;

/**
 * @Description 单例模式
 * @Author Wangkunkun
 * @Date 2018/06/27 21:17
 **/
public class SingletonTest {

    //饿汉式 不存在线程安全问题，但是用到次数少会浪费空间
    public static class Singleton1{
        private static Singleton1 singleton1 = new Singleton1();

        private Singleton1(){}

        public static Singleton1 getInstance(){
            return singleton1;
        }
    }

    //懒汉式 存在线程安全问题
    public static class Singleton2{
        private static Singleton2 singleton2 = null;

        private Singleton2(){}

        public static Singleton2 getInstance(){
            if(singleton2 == null){
                singleton2 = new Singleton2();
            }
            return singleton2;
        }
    }

    //懒汉式 线程安全 相对高效
    public static class Singleton3{
        private static Singleton3 singleton3 = null;

        private Singleton3(){}

        public static Singleton3 getInstance(){
            if(singleton3 == null){
                synchronized (Singleton3.class){
                    if(singleton3 == null){
                        singleton3 = new Singleton3();
                    }
                }
            }
            return singleton3;
        }
    }

    //静态内部类(懒汉+无锁) 利用类加载机制
    public static class Singleton4{

        private static class Singleton4Holder{
            private static Singleton4 singleton4 = new Singleton4();
        }

        private Singleton4(){}
        public static Singleton4 getInstance(){
            return Singleton4Holder.singleton4;
        }
    }

    //枚举
}
