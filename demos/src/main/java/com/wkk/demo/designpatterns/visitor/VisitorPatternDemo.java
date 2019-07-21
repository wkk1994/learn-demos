package com.wkk.demo.designpatterns.visitor;

import com.wkk.demo.designpatterns.visitor.consumer.Computer;
import com.wkk.demo.designpatterns.visitor.provider.ComputerPartDisplayVisitor;
import com.wkk.demo.designpatterns.visitor.provider.ComputerPartVisitor;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/07/08 14:02
 **/
public class VisitorPatternDemo {

    public static void main(String[] args) {
        ComputerPartVisitor visitor = new ComputerPartDisplayVisitor();
        Computer computer = new Computer();
        computer.accept(visitor);
    }
}
