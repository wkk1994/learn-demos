package com.wkk.demo.designpatterns.visitor.provider;


import com.wkk.demo.designpatterns.visitor.consumer.Computer;
import com.wkk.demo.designpatterns.visitor.consumer.Keyboard;
import com.wkk.demo.designpatterns.visitor.consumer.Monitor;
import com.wkk.demo.designpatterns.visitor.consumer.Mouse;

/**
 * @Description 访问者根据不同的
 * @Author wangkunkun
 * @Date 2018/07/08 13:44
 **/
public interface ComputerPartVisitor {

     void visit(Computer computer);
     void visit(Mouse mouse);
     void visit(Keyboard keyboard);
     void visit(Monitor monitor);
}
