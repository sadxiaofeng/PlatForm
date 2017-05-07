package com.service;

import com.pojo.User;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/16.
 */
public interface IUserService {
    User getUserByAccount(String account);

    List<User> getUserByClass(long classId);

    void createUser(User user);
}
