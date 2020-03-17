package com.whu.cs.dao;

import com.whu.cs.domain.Role;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/3/14
 * @blog:
 * @version:1.0.0
 * @description:
 */
public interface IRoleDao {
    /**
     * 查询所有带有用户信息的角色信息
     * @return
     */
    List<Role> findRolesWithUsers();
}
