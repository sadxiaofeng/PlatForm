package com.util;

import com.pojo.User;

/**
 * Created by 钱逊 on 2017/4/24.
 */
public class AutherUtil {

    public static boolean checkAuth(User user, int autherConfirm){
        if(user.getIdentity() != autherConfirm){
            return false;
        }
        return true;
    }
}
