package com.whu.cs.mybatis.sqlsession.defaults;

import com.whu.cs.mybatis.config.Configuration;
import com.whu.cs.mybatis.sqlsession.SqlSession;
import com.whu.cs.mybatis.sqlsession.SqlSessionFactory;


/**
 * @author: zhouqian
 * @date: 2020/2/4
 * @blog:
 * @version:1.0.0
 * @description: SqlSessionFactory接口的实现类
 */

public class DefualtSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public  DefualtSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    /**
     *@Description: 用于创建一个新的操作数据库对象
     *@Params:
     *@Return:
     *@Author: zhouqian
     *Date: 2020/2/4
    **/
    @Override
    public SqlSession openSession() {
        return new DefualtSqlSession(cfg);
    }
}
