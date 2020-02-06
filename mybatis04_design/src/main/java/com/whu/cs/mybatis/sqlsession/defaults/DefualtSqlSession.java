package com.whu.cs.mybatis.sqlsession.defaults;

import com.whu.cs.mybatis.config.Configuration;
import com.whu.cs.mybatis.config.Mapper;
import com.whu.cs.mybatis.sqlsession.SqlSession;
import com.whu.cs.mybatis.sqlsession.proxy.MapperProxy;
import com.whu.cs.mybatis.utils.DataSourceUtil;


import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author: zhouqian
 * @date: 2020/2/4
 * @blog:
 * @version:1.0.0
 * @description: SqlSession接口的实现类
 */

public class DefualtSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefualtSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     *@Description: 用于创建代理对象
     *@Params:
     *@Return:
     *@Author: zhouqian
     *Date: 2020/2/4
    **/
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
       return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass}, new MapperProxy(cfg.getMappers(), connection));
    }

    /**
     *@Description: 用于释放资源
     *@Params:
     *@Return:
     *@Author: zhouqian
     *Date: 2020/2/4
    **/
    @Override
    public void close(){
        if(connection != null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
