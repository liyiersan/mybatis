package com.whu.cs.dao;

import com.whu.cs.domain.Account;
import com.whu.cs.domain.AccountUser;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/28
 * @blog:
 * @version:1.0.0
 * @description:
 */
public interface IAccountDao {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 根据用户id查询账户信息
     * @param uid
     * @return
     */
    List<Account> findAccountsByUid(Integer uid);

    /**
     * 查询账户和用户信息
     * @return
     */
    List<AccountUser> findUserAndAccount();

    /**
     * 查询带有用户信息的账户
     * @return
     */
    List<Account> findAllAccountsWithUser();

}
