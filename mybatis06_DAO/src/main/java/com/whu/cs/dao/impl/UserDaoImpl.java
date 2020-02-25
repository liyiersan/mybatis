package com.whu.cs.dao.impl;

import com.whu.cs.dao.IUserDao;
import com.whu.cs.domain.QueryVo;
import com.whu.cs.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author: zhouqian
 * @date: 2020/2/12
 * @blog:
 * @version:1.0.0
 * @description:
 */

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;
    private SqlSession session;

    public UserDaoImpl(SqlSessionFactory factory){
        if(factory != null){
            this.factory = factory;
            session = factory.openSession();
        }
    }

    @Override
    public List<User> findAll() {
        //selectList参数为获取配置信息的key
        List<User> users = session.selectList("com.whu.cs.dao.IUserDao.findAll");
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        session.insert("com.whu.cs.dao.IUserDao.saveUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        session.update("com.whu.cs.dao.IUserDao.updateUser",user);
        session.commit();
        session.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        session.delete("com.whu.cs.dao.IUserDao.deleteUser",userId);
        session.commit();
        session.close();
    }

    @Override
    public User findById(Integer userId) {
        User user = session.selectOne("com.whu.cs.dao.IUserDao.findById",userId);
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String username) {
        List<User> users = session.selectList("com.whu.cs.dao.IUserDao.findByName",username);
        session.close();
        return users;
    }

    @Override
    public int findTotal() {
        int total = session.selectOne("com.whu.cs.dao.IUserDao.findTotal");
        session.close();
        return total;
    }

    @Override
    public List<User> findUserByVo(QueryVo vo) {
        List<User> users = session.selectList("com.whu.cs.dao.IUserDao.findUserByVo", vo);
        session.close();
        return users;
    }
}
