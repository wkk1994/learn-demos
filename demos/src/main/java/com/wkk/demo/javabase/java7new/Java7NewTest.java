package com.wkk.demo.javabase.java7new;


import java.io.*;
import java.sql.SQLException;

/**
 * @Description java7新特性
 * @Author wkk
 * @Date 2019-02-21 20:30
 **/
public class Java7NewTest {

    public static void test(){
        int i = 123; // 十进制
        System.out.println(i);
        i = 0123; // 八进制
        System.out.println(i);
        i = 0X12AB; // 十六进制
        System.out.println(i);
        i = 0B1_____0; // 二进制 Java7新增
        System.out.println(i);
        i= 0B1001;
        double d = 123.025;
    }

    public static void test1(int i){
        try{
            if(i == 1){
                throw new FileNotFoundException();
            }else {
                throw new SQLException();
            }

        }catch (FileNotFoundException|SQLException e){
            // e = new SQLException(); ERROR
            System.out.println(e);
        }
    }


    public static void test2() {
        FileOutputStream fileOutputStream = null;
        try {
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader("D:\\Log.txt"));
            String s = bufferedReader1.readLine();
            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void test3() {
        try (BufferedReader bufferedReader1 =
                     new BufferedReader(new FileReader("D:\\Log.txt"))){
            String s = bufferedReader1.readLine();
            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test4() {
        try {
            try (AutoCloseTest autoCloseTest =
                         new AutoCloseTest()){
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }
    public static void main(String[] args) {
        test2();
    }
}
