package com.whu.cs.mybatis.sqlsession;

import com.whu.cs.mybatis.config.Configuration;
import com.whu.cs.mybatis.sqlsession.defaults.DefualtSqlSessionFactory;
import com.whu.cs.mybatis.utils.*;

import java.io.InputStream;

/**
 * @author: zhouqian
 * @date: 2020/2/4
 * @blog:
 * @version:1.0.0
 * @description: 用于创建一个SqlSessionFactory对象
        */

public class SqlSessionFactoryBuilder {

    /**
     * @Description: 根据字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefualtSqlSessionFactory(cfg);
    }
}
