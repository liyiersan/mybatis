package com.whu.cs.test;

import com.whu.cs.domain.QueryVo;
import com.whu.cs.domain.User;
import com.whu.cs.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author: zhouqian
 * @date: 2020/2/12
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;


    @Before//用于在测试方法执行之前运行
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后运行
    public void close() throws IOException {
        //5.提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws IOException {
        //执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 测试根据用户ID查询用户
     *
     * @throws IOException
     */
    @Test
    public void testFindById() throws IOException {
        User user = userDao.findById(50);
        System.out.println(user);
    }

    /**
     * 测试根据名称模糊查询用户
     *
     * @throws IOException
     */
    @Test
    public void testFindByName() throws IOException {
        //List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for (User user : users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据封装pojo对象进行查询
     *
     * @throws IOException
     */
    @Test
    public void testFindByVo() throws IOException {
        QueryVo vo = new QueryVo();
        User u = new User();
        u.setUsername("%王%");
        vo.setUser(u);
        List<User> users = userDao.findUserByVo(vo);
        for (User user : users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据动态条件进行查询
     *
     * @throws IOException
     */
    @Test
    public void testFindByCondition() throws IOException {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("老王");
        vo.setUser(user);
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        ids.add(43);
        vo.setIds(ids);
        List<User> users = userDao.findUserByCondition(user);
        for (User u : users
        ) {
            System.out.println(u);
        }
    }

    /**
     * 测试集合中的id值进行查询
     *
     * @throws IOException
     */
    @Test
    public void testFindInIds() throws IOException {
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        ids.add(43);
        vo.setIds(ids);
        List<User> users = userDao.findUserInIds(vo);
        for (User user : users
        ) {
            System.out.println(user);
        }
    }
}
