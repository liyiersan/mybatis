package com.whu.cs.domain;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/12
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class QueryVo {
    private User user;
    private List<Integer> ids;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
