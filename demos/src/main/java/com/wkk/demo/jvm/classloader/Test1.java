package com.wkk.demo.jvm.classloader;

/**
 * @Description
 * @Date 2020/10/16 12:51
 */
public class Test1 {

    public static void main(String[] args) {
        byte[] bytes = "cafebabe".getBytes();
        // CAFEBABE
        // 6361
        int i = 0xcafebabe;
        System.out.println(i);
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        System.out.println(bytes);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }
        System.out.println(buf);


    }
}
