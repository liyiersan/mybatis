package com.whu.cs.mybatis.sqlsession;

/**
 * @author: zhouqian
 * @date: 2020/2/4
 * @blog:
 * @version:1.0.0
 * @description: 自定义mybatis中和数据库交互的核心类，它可以创建dao接口的代理对象
 */
public interface SqlSession {

    /**
     * @description 根据参数创建一个代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    //释放资源
    void close();
}
