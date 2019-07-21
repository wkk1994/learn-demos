package com.wkk.demo.javabase.reflex;

/**
 * @Description
 * @Author wkk
 * @Date 2019-01-19 13:49
 **/
public abstract class ParentAbstart {

    public String parentAbs = "public-parentAbs";

    private String parentAbs1 = "private-parentAbs";

    public String getParentAbs() {
        return parentAbs;
    }

    public void setParentAbs(String parentAbs) {
        this.parentAbs = parentAbs;
    }

    public String getParentAbs1() {
        return parentAbs1;
    }

    public void setParentAbs1(String parentAbs1) {
        this.parentAbs1 = parentAbs1;
    }
}
