package com.wkk.demo.javabase.reflex;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description 反射测试
 * @Author wkk
 * @Date 2019-01-19 13:50
 **/
public class Reflex extends ParentAbstart implements ParentInter {

    public String ref = "public - Reflex";

    private String refPrivate = "refPrivate";

    public String getRefPrivate() {
        return refPrivate;
    }

    public void setRefPrivate(String refPrivate) {
        this.refPrivate = refPrivate;
    }

    public static void main(String[] args) throws Exception {
        Class<Reflex> reflexClass = Reflex.class;
        Field[] fields = reflexClass.getFields();
        for (Field field : fields) {
            System.out.println("getFields --"+ field.getName());
        }

        Field[] declaredFields = reflexClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("getDeclaredFields --"+ declaredField.getName());
        }

        Method[] methods = reflexClass.getMethods();
        for (Method method : methods) {
            System.out.println("getMethods -- "+ method.getName());
        }
        Method[] declaredMethods = reflexClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("getDeclaredMethods -- "+ declaredMethod.getName());
        }
        // 反射执行方法
        Class<?> klass = String.class;
        Constructor<?> constructor = klass.getConstructor(String.class);
        Object object = constructor.newInstance("test");
        Method method = klass.getMethod("equals",Object.class);
        Object result = method.invoke(object,"test");
        System.out.println(result);
        //反射创建数组
        Class zlass = Class.forName("java.lang.String");
        Object array = Array.newInstance(zlass,25);
        Array.set(array,0,"0");
        Array.set(array,1,"1");
        Array.set(array,2,"2");
        Array.set(array,3,"3");
        System.out.println(Array.get(array,2));
    }
}
