package com.whu.cs.test;

import com.whu.cs.dao.IAccountDao;
import com.whu.cs.domain.Account;
import com.whu.cs.domain.AccountUser;
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
 * @date: 2020/2/28
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class AccountTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;


    @Before//用于在测试方法执行之前运行
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        accountDao = sqlSession.getMapper(IAccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * 测试查询账户和用户信息
     *
     * @throws IOException
     */
    @Test
    public void testFindUserAndAccount() throws IOException {
        List<AccountUser> ausers = accountDao.findUserAndAccount();
        for (AccountUser auser : ausers
        ) {
            System.out.println(auser);
        }
    }

    /**
     * 测试以延迟加载的方式查询带有用户信息的账户信息
     *
     * @throws IOException
     */
    @Test
    public void testFindAllAccountsWithUser() throws IOException {
        List<Account> accounts = accountDao.findAllAccountsWithUser();
        int i = 0;
        for (Account account : accounts
        ) {
            System.out.println(account.getMoney());
            if(i == 1)
                System.out.println(account.getUser()); //只有当需要user信息时，才对user进行查询
            i++;
//            System.out.println(account);//这里表明每次查询都需要加载完整数据
//            System.out.println(account.getUser());
        }
    }
}
