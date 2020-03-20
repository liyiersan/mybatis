package com.whu.cs;

import com.whu.cs.dao.IAccountDao;
import com.whu.cs.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/3/18
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAllAccounts() {
        List<Account> accounts = accountDao.findAllAccounts();
        for (Account account : accounts
        ) {
            System.out.println(account);
        }
    }


    /**
     * 测试查询带有用户信息的账户信息
     */
    @Test
    public void testFindAccountsWithUser() {
        List<Account> accounts = accountDao.findAccountsWithUser();
        for (Account account : accounts
        ) {
            System.out.println(account.getId());
            if(account.getUid() == 41)
                System.out.println(account.getUser());
        }
    }

    @Test
    public void testFindByUid() {
        List<Account> accounts = accountDao.findByUid(41);
        for (Account account : accounts
        ) {
            System.out.println(account);
        }
    }


}
