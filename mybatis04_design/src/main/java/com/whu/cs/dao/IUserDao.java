package com.whu.cs.dao;

import com.whu.cs.domain.User;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/1
 * @blog:
 * @version:1.0.0
 * @description:
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();
}
