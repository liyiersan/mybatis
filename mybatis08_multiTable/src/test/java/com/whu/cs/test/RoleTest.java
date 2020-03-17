package com.whu.cs.test;

import com.whu.cs.dao.IRoleDao;
import com.whu.cs.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/3/14
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class RoleTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao roleDao;


    @Before//用于在测试方法执行之前运行
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        roleDao = sqlSession.getMapper(IRoleDao.class);
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
    public void testFindRolesWithUsers(){
        List<Role> roles = roleDao.findRolesWithUsers();
        for(Role role : roles){
            System.out.println("---每个角色的信息----");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
}
