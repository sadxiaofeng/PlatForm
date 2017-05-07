package com.service.imp;

import com.dao.IUserDao;
import com.pojo.User;
import com.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/16.
 */
@Service
public class UserService implements IUserService {
    @Resource
    IUserDao userDao;

    @Override
    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }

    @Override
    public List<User> getUserByClass(long classId) {
        return userDao.getUserByClass(classId);
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
