package com.whu.cs.dao;

import com.whu.cs.domain.AccountUser;
import com.whu.cs.domain.QueryVo;
import com.whu.cs.domain.User;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/28
 * @blog:
 * @version:1.0.0
 * @description:
 */
public interface IUserDao {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据用户Id删除用户
     *
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据用户Id查询用户
     *
     * @param useId
     * @return
     */
    User findById(Integer useId);

    /**
     * 根据名称模糊查询用户信息
     *
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总的用户数
     *
     * @return
     */
    int findTotal();

    /**
     * 封装pojo对象来进行查询
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 以延迟加载的方式查询带有账户信息的用户信息
     * @return
     */
    List<User> findUserWithAccounts();

}
