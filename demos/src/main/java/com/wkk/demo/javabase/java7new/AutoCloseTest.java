package com.wkk.demo.javabase.java7new;

import java.io.FileNotFoundException;

/**
 * @Description 自动关闭测试
 * @Author wkk
 * @Date 2019-02-21 21:27
 **/
public class AutoCloseTest implements AutoCloseable {
    @Override
    public void close() throws Exception {
        //throw new FileNotFoundException("13");
    }
}
