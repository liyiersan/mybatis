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
import java.util.Date;
import java.util.List;

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
     * 测试保存用户
     *
     * @throws IOException
     */
    @Test
    public void testSaveUser() throws IOException {
        User user = new User();
        user.setUserName("li yier");
        user.setUserAddress("湖北省武汉市");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("插入数据库之前：" + user);
        userDao.saveUser(user);
        System.out.println("插入数据库之后：" + user);
    }

    /**
     * 测试更新用户
     *
     * @throws IOException
     */
    @Test
    public void testUpdateUser() throws IOException {
        User user = new User();
        user.setUserId(50);
        user.setUserName("li san 2");
        user.setUserAddress("湖北省武汉市");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        userDao.updateUser(user);
    }

    /**
     * 测试删除用户
     *
     * @throws IOException
     */
    @Test
    public void testDeleteUser() throws IOException {
        userDao.deleteUser(53);
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
        List<User> users = userDao.findByName("%王%");
        //List<User> users = userDao.findByName("王");
        for (User user : users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据查询用户记录条数
     *
     * @throws IOException
     */
    @Test
    public void testFindTotal() throws IOException {
        int total = userDao.findTotal();
        System.out.println("用户总数为：" + total);
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
        u.setUserName("%王%");
        vo.setUser(u);
        List<User> users = userDao.findUserByVo(vo);
        for (User user : users
        ) {
            System.out.println(user);
        }
    }
}
