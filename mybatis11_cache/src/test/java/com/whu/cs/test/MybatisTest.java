package com.whu.cs.test;

import com.whu.cs.dao.IUserDao;
import com.whu.cs.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author: zhouqian
 * @date: 2020/2/1
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class MybatisTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.创建 SqlSession 工厂对象
        factory = builder.build(in);
        //4.创建 SqlSession 对象
        sqlSession = factory.openSession();
        //5.创建 Dao 的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//在测试方法执行完成之后执行
    public void destroy() throws Exception {
        //7.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testL1Cache() {

        User user1 = userDao.findById(41);
        System.out.println("第一次查询的用户:" + user1);

        User user2 = userDao.findById(41);
        System.out.println("第二次查询用户:" + user2);
        System.out.print("第一次和第二次是否是同一对象：");
        System.out.println(user1 == user2);

        sqlSession.clearCache();//清空缓存
        User user3 = userDao.findById(41);
        System.out.println("第三次查询用户:" + user3);
        System.out.print("第二次和第三次是否是同一对象：");
        System.out.println(user2 == user3);

        sqlSession.close();//close操作会清空缓存
        //再次获取 SqlSession 对象
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
        User user4 = userDao.findById(41);
        System.out.println("第四次查询用户:" + user4);
        System.out.print("第三次和第四次是否是同一对象：");
        System.out.println(user3 == user4);

    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testL2Cache() {

        User user1 = userDao.findById(41);
        System.out.println("第一次查询的用户:" + user1);
        sqlSession.close(); //关闭一级缓存

        SqlSession sqlSession2 = factory.openSession();
        IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = userDao2.findById(41);
        System.out.println("第二次查询用户:" + user2);
        System.out.print("第一次和第二次是否是同一对象：");
        System.out.println(user1 == user2);
        sqlSession2.close();

    }


}
