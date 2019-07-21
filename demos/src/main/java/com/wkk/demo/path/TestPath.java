package com.wkk.demo.path;

import java.io.*;
import java.net.URL;

/**
 * @Description java获取相对路径、绝对路径方式
 * @Author wangkunkune
 * @Date 2018/06/21 16:21
 **/
public class TestPath {

    public static void main(String[] args) throws IOException {

        URL resource = TestPath.class.getResource("");
        System.out.println("URI目录,不包括自己:"+resource);

        resource = TestPath.class.getResource("/");
        System.out.println("当前的classpath的绝对URI路径:"+resource);

        resource = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println("当前的classpath的绝对URI路径:"+resource);

        resource = TestPath.class.getClassLoader().getResource("");
        System.out.println("当前的classpath的绝对URI路径:"+resource);

        resource = ClassLoader.getSystemResource("");
        System.out.println("当前的classpath的绝对URI路径:"+resource);

        //1. Class.getResourceAsStream(String path) ：  path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从
        // ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
        // 2. Class.getClassLoader.getResourceAsStream(String path) ： 默认则是从ClassPath根下获取，path不能以’/'开头，
        // 最终是由   ClassLoader获取资源。 TODO
        InputStream resourceAsStream = TestPath.class.getClassLoader().getResourceAsStream("text.txt");
        InputStreamReader  reader = new InputStreamReader(resourceAsStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ( (line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }


    }
}
