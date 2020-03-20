package com.whu.cs.dao;

import com.whu.cs.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/3/18
 * @blog:
 * @version:1.0.0
 * @description:
 */

public interface IAccountDao {

    @Select("select * from account")
    List<Account> findAllAccounts();

    /**
     * 根据用户Id查询账户信息
     * @param uerId
     * @return
     */
    @Select("select * from account where uid = #{uid}")
    List<Account> findByUid(Integer uerId);

    /**
     * 查询带有用户信息的账户信息
     * 并且使用延迟加载的方式
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            //Result的one属性，表示是一对一的关系
            //此时column表示查询所需要的参数所对应的数据库列名
            //property表示的是从表实体对主表实体的引用的属性名
            @Result(column = "uid", property = "user",
                    //One的select属性，表示对于主表实体所对应的查询方法
                    //fetchType属性是加载的方式：LAZY(延迟)、EAGER(立即)、DEFAULT(默认)
                    //一对一默认是立即加载，一对多默认是延迟加载
                    one = @One(select = "com.whu.cs.dao.IUserDao.findById",
                            fetchType = FetchType.LAZY)
            )
    })
    List<Account> findAccountsWithUser();



}
