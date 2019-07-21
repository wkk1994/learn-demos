package com.wkk.demo.designpatterns.visitor.provider;

import com.wkk.demo.designpatterns.visitor.consumer.Computer;
import com.wkk.demo.designpatterns.visitor.consumer.Keyboard;
import com.wkk.demo.designpatterns.visitor.consumer.Monitor;
import com.wkk.demo.designpatterns.visitor.consumer.Mouse;

/**
 * @Description
 * @Author wangkunkun 访问者
 * @Date 2018/07/08 14:00
 **/
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }
}
