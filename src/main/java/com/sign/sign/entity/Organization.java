package com.sign.sign.entity;


import javax.persistence.*;
import java.util.List;

/**
 * 组织实体
 * 存入数据库,组织可由具有创建组织的权限的用户创建,
 * 一个组织可以由具有创建子组织权限的用户创建一个或多个(有限个)子组织,
 * 具有当前组织管理权限(组合权限)的用户可以管理当前组织
 *
 * @author haotian
 */
@Entity(name = "ORGANIZATION")
public class Organization {

    @Id
    private int id;

    /**
     * 组织名称
     */
    private String name;
    /**
     * 组织编码
     */
    private String number;
    /**
     * 上级id
     */
    private int parent;
    /**
     * 组织类型
     */
    private int type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "organization",
            joinColumns = {
                    @JoinColumn(name = "parent",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id",referencedColumnName = "id")
            }
    )
    private List<Organization> organizations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "organization_user",
            joinColumns = {
                    @JoinColumn(name = "organization_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id",referencedColumnName = "id")
            }
    )
    private List<User> users;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
