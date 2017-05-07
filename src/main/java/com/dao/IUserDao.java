package com.dao;

import com.pojo.User;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/16.
 */
public interface IUserDao {
    public User getUserByAccount(String account);

    public User getUserById(long id);

    public List<User> getUserByClass(long classId);

    public void createUser(User user);

    public void update(User user);
}
