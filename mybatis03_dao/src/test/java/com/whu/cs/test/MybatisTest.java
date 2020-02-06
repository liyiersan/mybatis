package com.whu.cs.test;

import com.whu.cs.dao.IUserDao;
import com.whu.cs.dao.impl.UserDaoImpl;
import com.whu.cs.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/1
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class MybatisTest {
    public static void main(String[] args) throws Exception{
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂创建dao对象
        IUserDao userDao = new UserDaoImpl(factory);
        //4.使用dao对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
        //5.释放资源
        in.close();

    }
}
