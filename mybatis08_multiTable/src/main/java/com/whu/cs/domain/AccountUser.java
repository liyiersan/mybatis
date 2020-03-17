package com.whu.cs.domain;

/**
 * @author: zhouqian
 * @date: 2020/2/29
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class AccountUser extends Account {

    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() +
                "   AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
