package com.wkk.demo.javabase.java8new;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description java8新特性
 * @Author wkk
 * @Date 2019-02-23 10:32
 **/
public class Java8NewTest {

    // Lambda表达式
    public static void test(){
        Arrays.asList("a","b","c").forEach(e -> System.out.println(e));

        String str = ",";
        Arrays.asList("a","b","c").forEach((e -> System.out.println(e + str)));

        Arrays.asList("a","b","c").sort((e1,e2) -> e1.compareTo(e2));
        Arrays.asList("a","b","c").sort((e1,e2) -> {
            int i = e1.compareTo(e2);
            return i;
        });

        Arrays.asList("a","b","c").forEach((e -> new Functional(){
            @Override
            public void test(String e) {
                System.out.println("ss"+e);
            }
        }.test(e)));

    }

    //Nashorn JavaScript引擎
    public static void test3() throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine javaScript = scriptEngineManager.getEngineByName("JavaScript");
        System.out.println(javaScript.getClass().getName());
        System.out.println(javaScript.eval("function f(){return 1;} f()+2"));;
    }

    // Base64
    public static void test4() throws UnsupportedEncodingException {
        String str = "Hello Base64!";
        byte[] encode = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);
        String encodeString = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeString);
        byte[] decode = Base64.getDecoder().decode(encodeString);
        String text = new String(decode,StandardCharsets.UTF_8);
        System.out.println(text);
        String encode1 = Base64.getMimeEncoder().encodeToString("text/html".getBytes("UTF-8"));
        System.out.println(encode1);
        byte[] decode1 = Base64.getMimeDecoder().decode(encode1);
        System.out.println(new String(decode1,"utf-8"));
    }

    //数组并行操作
    public static void test5(){
        long[] arr = new long[2000];
        Arrays.parallelSetAll(arr,index -> ThreadLocalRandom.current().nextInt(2000));
        Arrays.stream(arr).limit(10).forEach(x-> System.out.println(x));
        System.out.println("===========");
        //排序
        Arrays.parallelSort(arr);
        Arrays.stream(arr).limit(10).forEach(x-> System.out.println(x));
    }
    public static void main(String[] args) throws Exception {
        test5();
        test4();
        test();
        test3();
        Method main = Java8NewTest.class.getMethod("main", String[].class);
        Parameter[] parameters = main.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getType() + "===="+parameter.isNamePresent()+"====" + parameter.getName());
        }
    }
}
