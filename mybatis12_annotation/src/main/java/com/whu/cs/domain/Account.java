package com.whu.cs.domain;

import java.io.Serializable;

/**
 * @author: zhouqian
 * @date: 2020/3/18
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class Account implements Serializable {
    private Integer uid;
    private Integer id;
    private Double money;

    //从表实体应包含主表实体的引用
    private User user;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uid=" + uid +
                ", id=" + id +
                ", money=" + money +
                '}';
    }
}
