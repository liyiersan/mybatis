package com.whu.cs.dao;

import com.whu.cs.domain.User;
import org.apache.ibatis.annotations.Select;

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
     * 根据id查询用户操作
     * @return
     */
    User findById(Integer id);
}
