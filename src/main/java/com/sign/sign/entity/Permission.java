package com.sign.sign.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "PERMISSION")
public class Permission {

    @Id
    private int id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限说明
     */
    private String note;
    /**
     * 上级id
     */
    private int parent;
    /**
     * 权限编码
     */
    private String number;

    /**
     * 系统编码
     */
    private String systemNumber;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 排序
     */
    private int sort;




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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }


}
