package com.wkk.demo.references.entity;

/**
 * @Description
 * @Author onepiece
 * @Date 2018/06/10 20:45
 **/
public class Entity {

    private int id;

    private String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity() {
    }
}
