package com.wkk.demo.javabase.fanxing.factory;

/**
 * @Description
 * @Author wkk
 * @Date 2019-01-29 21:50
 **/
public class TestOnePart extends Part {

    public static class Factory implements com.wkk.demo.javabase.fanxing.factory.Factory<TestOnePart>{

        @Override
        public TestOnePart create() {
            return new TestOnePart();
        }
    }
}
