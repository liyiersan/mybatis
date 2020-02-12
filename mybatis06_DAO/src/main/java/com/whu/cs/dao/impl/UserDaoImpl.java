package com.whu.cs.dao.impl;

import com.whu.cs.dao.IUserDao;
import com.whu.cs.domain.QueryVo;
import com.whu.cs.domain.User;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/12
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public User findById(Integer useId) {
        return null;
    }

    @Override
    public List<User> findByName(String username) {
        return null;
    }

    @Override
    public int findTotal() {
        return 0;
    }

    @Override
    public List<User> findUserByVo(QueryVo vo) {
        return null;
    }
}
