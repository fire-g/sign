package com.sign.sign.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ROLE")
public class Role {
    @Id
    private int id;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色说明
     */
    private String note;
    /**
     * 角色编码
     */
    private String number;
    /**
     * 上级id
     */
    private int parent;
    /**
     * 系统编码
     */
    private String systemNumber;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }
}
