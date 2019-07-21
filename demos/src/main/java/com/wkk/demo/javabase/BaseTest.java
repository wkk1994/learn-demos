package com.wkk.demo.javabase;

import java.util.Random;

/**
 * @Description java基础demo
 * @Author wkk
 * @Date 2019-01-16 19:52
 **/
public class BaseTest {

    /**
     * intern测试
     */
    public static void stringTest1(){
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2);// false
        String s3 = new String("1");
        System.out.println(s3 == s2);// false
        String s4 = new String("1") + new String("1");
        s4.intern();
        String s5 ="11";
        System.out.println(s4 == s5);// true
        String s6 ="1"+"1";
        System.out.println(s4 == s6);// true
    }

    /**
     * intern测试
     */
    public static void stringTest2(){
        String s1 = new String("1");
        String s2 = "1";
        s1.intern();
        System.out.println(s1 == s2);// false
        String s3 = new String("1");
        System.out.println(s3 == s1);// false
        String s4 = new String("1") + new String("1");
        String s5 ="11";
        s4.intern();
        System.out.println(s4 == s5);// false
        String s6 ="1"+"1";
        System.out.println(s4 == s6);// false
    }

    /**
     * 合理利用intern方法，减少内存使用
     * 对经常使用的字符串存进常量池中减小内存消耗
     */
    public static void internTest(){
        Integer[] data = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt();
        }
        int max = 1000 * 10000;
        String[] arr = new String[max];
        long t = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            //arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            arr[i] = new String(String.valueOf(data[i % data.length])).intern();
        }

        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();
    }

    public static void main(String[] args) {
        internTest();
        short s1 = 1;
        //s1 = s1 +1;
        s1 += 1;
        s1++;
        int de = 2147483647;
        System.out.println(de);
        System.out.println(de+1);
        de = -2147483648;
        System.out.println(de);
        System.out.println(de-1);
        int ii = 1;
        for (int i = 0; i < 500; i++) {
            ii = ii * i;
        }
        System.out.println(ii);
        System.out.println(test());
    }

    public static String test(){
        try{
            System.out.println("try");
            throw new Exception("tes");
        }catch (Exception e){
            System.out.println("Exception");
            return "Exception";
        }finally {
            System.out.println("finally");
        }
        /*try (FileInputStream baseTest = new FileInputStream("")) {
            Class<? extends FileInputStream> aClass = baseTest.getClass();
            System.out.println("MyResource created in try-with-resources");
            return "String";
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
