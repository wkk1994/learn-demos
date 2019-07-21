package com.wkk.demo.designpatterns.visitor.consumer;

import com.wkk.demo.designpatterns.visitor.provider.ComputerPartVisitor;

/**
 * @Description 被访问者 暴露数据结构给访问者
 * @Author wangkunkun
 * @Date 2018/07/08 13:44
 **/
public interface ComputerPart {

    public void accept(ComputerPartVisitor computerPartVisitor);
}
