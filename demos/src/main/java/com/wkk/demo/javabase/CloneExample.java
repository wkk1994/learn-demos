package com.wkk.demo.javabase;

/**
 * @Description CloneExample
 * @Author wkk
 * @Date 2019-01-17 20:44
 **/
public class CloneExample implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        CloneExample cloneExample = new CloneExample();
        try {
            cloneExample.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
