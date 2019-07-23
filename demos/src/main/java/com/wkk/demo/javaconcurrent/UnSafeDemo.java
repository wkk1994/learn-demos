package com.wkk.demo.javaconcurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Description UnSafeDemo
 * @Author wangkunkun
 * @Date 2019/04/04 09:21
 **/
public class UnSafeDemo {

    private static Unsafe unsafe;

    static{
        try {
            // 通过反射得到theUnsafe对应的Field对象
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
            unsafe = (Unsafe) theUnsafe.get(null);
            System.out.println(unsafe);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // unsageTest();
        casTest();
    }

    private static void unsageTest() throws InstantiationException, NoSuchFieldException {
        // 获取User的属性偏移量
        Class<User> userClass = User.class;
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                System.out.println(declaredField.getName()+" : "+unsafe.staticFieldOffset(declaredField));
            }else {
                System.out.println(declaredField.getName()+" : "+unsafe.objectFieldOffset(declaredField));
            }
        }
        //通过allocateInstance直接创建对象
        User user = (User) unsafe.allocateInstance(User.class);
        System.out.println(user);
        //获取实例变量name和age在对象内存中的偏移量并设置值
        unsafe.putInt(user,unsafe.objectFieldOffset(userClass.getDeclaredField("age")),200);
        unsafe.putObject(user,unsafe.objectFieldOffset(userClass.getDeclaredField("name")),"hello");
        // 设置静态变量值
        unsafe.putObject(user.getClass(),unsafe.staticFieldOffset(userClass.getDeclaredField("id")),"NEW_ID");
        System.out.println(user);
        System.out.println(unsafe.getObject(user, unsafe.objectFieldOffset(userClass.getDeclaredField("name"))));

        //直接分配地址
        byte size = 2;
        long memory = unsafe.allocateMemory(size);
        System.out.println("内存地址: "+memory);
        unsafe.putAddress(memory,2000);
        long addrData=unsafe.getAddress(memory);
        System.out.println("addrData:"+addrData);
    }

    public static void test() throws Exception {
        //反射获取Unsafe
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        User user = (User) unsafe.allocateInstance(User.class);
        System.out.println(user);
        unsafe.putObject(user, unsafe.objectFieldOffset(User.class.getDeclaredField("name")), "测试");
        System.out.println(user.name);
    }

    private static void casTest() throws Exception {
        //反射获取Unsafe
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        User user = (User) unsafe.allocateInstance(User.class);
        long ageOffset = unsafe.objectFieldOffset(User.class.getDeclaredField("age"));
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                int anInt = unsafe.getInt(user, ageOffset);
                boolean b = unsafe.compareAndSwapInt(user, ageOffset, anInt, ++anInt);
                if(b){
                    System.out.println(finalI + " boolean : "+ unsafe.getInt(user, ageOffset));
                }else {
                    System.out.println(finalI + " boolean : "+ b);
                }

            });
            thread.start();
        }


    }

    static class User{
        public User(){
            System.out.println("user 构造方法被调用");
        }
        private String name;
        private int age;
        private static String id="USER_ID";

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +'\'' +
                    ", id=" + id +'\'' +
                    '}';
        }
    }
}
