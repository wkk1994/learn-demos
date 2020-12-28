package com.wkk.demo.jvm.classloader;

/**
 * @Description 类加载器测试
 * @Author Wangkunkun
 * @Date 2020/10/14 16:17
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        TestB[] testBS = new TestB[3];
        testBS[0] = null;
        System.out.println("------");
        System.out.println(TestB.STR);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        classLoader.loadClass("com.wkk.demo.jvm.classloader.Test");
        System.out.println("----classLoader.loadClass----");
        //使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("com.wkk.demo.jvm.classloader.Test");
        System.out.println("----Class.forName----");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        Class.forName("com.wkk.demo.jvm.classloader.Test", false, classLoader);

        System.out.println("---------");
        TestB[] testBS1 = new TestB[1];
        Class<TestB> testBClass = TestB.class;
        //Class.forName("com.wkk.demo.jvm.classloader.TestA", true, classLoader);
        System.out.println(TestB.STR);
    }
}

class Test {
    static {
        System.out.println("静态代码块被执行");
    }
}

class TestA {
    public static String STR = "123";

    static {
        System.out.println("TestA静态代码块被执行");
    }
}

class TestB extends TestA {

    static {
        System.out.println("TestB静态代码块被执行");
    }
}