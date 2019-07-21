package com.wkk.demo.javabase.fanxing.factory;

/**
 * @Description
 * @Author wkk
 * @Date 2019-01-29 21:50
 **/
public class TestTwoPart extends Part {

    public static class Factory implements com.wkk.demo.javabase.fanxing.factory.Factory<TestTwoPart>{

        @Override
        public TestTwoPart create() {
            return new TestTwoPart();
        }
    }
}
