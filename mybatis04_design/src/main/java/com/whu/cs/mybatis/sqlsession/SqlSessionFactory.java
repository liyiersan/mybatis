package com.whu.cs.mybatis.sqlsession;

/**
 * @author: zhouqian
 * @date: 2020/2/4
 * @blog:
 * @version:1.0.0
 * @description:
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
