package com.whu.cs;

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
import java.util.Date;
import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/3/18
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class UserTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有用户
     */
    @Test
    public void testFindAllUsers() {
        List<User> users = userDao.findAllUsers();
        for (User user : users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据id查询用户
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(41);
        System.out.println(user);
    }

    /**
     * 测试根据名字进行模糊查询
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User user : users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 查询数据库中用户总数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.print("数据库中用户总数为：");
        System.out.println(total);
    }

    /**
     * 测试插入用户
     */
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUserName("插入的用户");
        user.setUserAddress("湖北省武汉市洪山区");
        userDao.insertUser(user);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserId(59);
        user.setUserName("更新的用户");
        user.setUserAddress("北京市海淀区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        userDao.updateUser(user);
    }

    /**
     * 测试删除用户
     */
    @Test
    public void testDeleteUser(){
        User user = new User();
        userDao.deleteUser(60);
    }

    /**
     * 测试保存用户
     */
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUserName("保存的用户");
        user.setUserAddress("武汉市武昌区");
        user.setUserSex("女");
        user.setUserBirthday(new Date());
        int res = userDao.saveUser(user);
        System.out.println("影响数据库记录的行数:"+res);
        System.out.println("插入的主键值:"+user.getUserId());
    }

    /**
     * 测试查询带有账户信息的用户信息
     * 延迟加载的方式
     */
    @Test
    public void testFindUsersWithAccounts(){
        List<User> users = userDao.findUsersWithAccounts();
        for (User user : users
        ) {
            System.out.println(user.getUserId());
            if(user.getUserId() == 41)
                System.out.println(user.getAccounts());
        }
    }

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

        sqlSession = factory.openSession();
        userDao = sqlSession .getMapper(IUserDao.class);
        User user2  = userDao .findById(41);
        System.out.println("第二次查询用户:" + user2);
        System.out.print("第一次和第二次是否是同一对象：");
        System.out.println(user1 == user2);

    }

}
