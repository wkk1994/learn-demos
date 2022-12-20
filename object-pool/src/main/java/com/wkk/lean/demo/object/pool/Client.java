package com.wkk.lean.demo.object.pool;

/**
 * @author Wangkunkun
 * @date 2022/12/17 22:25
 */
public class Client {

    private Integer number;

    public Client(Integer number) {
        this.number = number;
    }

    public void printlnInfo() {
        System.out.println("client number : " + number);
    }

    @Override
    public String toString() {
        return "Client{" +
                "number=" + number +
                '}';
    }
}
