package com.whu.cs.dao;

import com.whu.cs.domain.User;
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

@CacheNamespace(blocking=true)  //mybatis 基于注解方式实现配置二级缓存
public interface IUserDao {

    /**
     * 查询所有用户
     * 实体类属性名和数据库列名不一致，通过Results注解进行配置
     * @return
     */
    @Select("select * from user")
    //Results的id相当于名称，value属性是实体类属性和数据库列名的映射关系
    @Results(id = "userMap", value = {
            //Result的id表示是否是主键，默认为false，column是数据库列名，property是实体类属性
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "birthday", property = "userBirthday"),
    }
    )
    List<User> findAllUsers();

    /**
     * 根据用户id查询用户
     *  无需再次重写Results注解，可以通过ResultMap注解引用Results
     * @param userId
     */
    @Select("select * from user where id = #{uid}")
    @ResultMap("userMap")
    User findById(Integer userId);

    /**
     * 模糊查询
     *  参数是基本属性，#{}中的名称随意
     * @param userName
     * @return
     */
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    List<User> findByName(String userName);

    /**
     * 查询用户总数
     *
     * @return
     */
    @Select("select count(id) from user")
    int findTotal();

    /**
     * 插入用户
     *
     * @param user
     */
    @Insert("insert into user(username, sex, address, birthday) values " +
            "(#{userName}, #{userSex}, #{userAddress}, #{userBirthday}) ")
    @ResultMap("userMap")
    void insertUser(User user);


    /**
     * 删除用户
     *
     * @param userId
     */
    @Delete("delete from user where id = #{id} ")
    @ResultMap("userMap")
    void deleteUser(Integer userId);

    /**
     * 更新用户
     * 参数是实体类，使用的OGNL表达式，因此#{}中的名称必须是属性名
     * @param user
     */
    @Update("update user set username = #{userName}, address = #{userAddress}, " +
            " sex = #{userSex}, birthday = #{userBirthday} where id = #{userId}")
    @ResultMap("userMap")
    void updateUser(User user);


    /**
     * 保存操作
     * 通过SelectKey可以获取保存用户时，数据库自动更新的id字段
     * @param user
     * @return 影响数据库记录的行数
     */
    @Insert("insert into user(username, sex, birthday, address) values (#" +
            "{userName}, #{userSex},#{userBirthday},#{userAddress} )")
    @SelectKey(keyColumn = "id", keyProperty = "userId", resultType = Integer.class,
            before = false, statement = {"select last_insert_id()"})
    int saveUser(User user);


    /**
     * 查询带有账户信息的用户信息
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap1", value = {
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(column = "id", property = "accounts",
                    //many表示对应关系是一对多
                    many = @Many(select = "com.whu.cs.dao.IAccountDao.findByUid",
                            fetchType = FetchType.LAZY))
    }
    )
    List<User> findUsersWithAccounts();
}
