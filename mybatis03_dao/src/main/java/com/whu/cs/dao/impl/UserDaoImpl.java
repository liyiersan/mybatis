package com.whu.cs.dao.impl;

import com.whu.cs.dao.IUserDao;
import com.whu.cs.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.xml.stream.FactoryConfigurationError;
import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/2
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
       this.factory = factory;
    }

    public List<User> findAll(){
        //1.使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        //2.使用session执行查询所有方法
        List<User> users = session.selectList("com.whu.cs.dao.IUserDao.findAll");
        session.close();
        //3.返回查询结果
        return users;
    }
}
