package com.wkk.demo.designpatterns.visitor.consumer;

import com.wkk.demo.designpatterns.visitor.provider.ComputerPartVisitor;

/**
 * @Description
 * @Author onepiece
 * @Date 2018/07/08 13:59
 **/
public class Mouse  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

