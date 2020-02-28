package com.whu.cs.dao;

import com.whu.cs.domain.QueryVo;
import com.whu.cs.domain.User;

import java.util.List;

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
     * 封装pojo对象来进行查询
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入的参数条件来查询
     * @param user 查询的条件：可能部分属性为空，也可能全部属性都不为空
     * @return
     */
    List<User> findUserByCondition(User user);


    /**
     * 根据QueryVo中提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);

    /**
     * 根据list中的id值，查询用户信息
     * @param list
     * @return
     */
    List<User> findUsersByIds(List<Integer> list);
}
