package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * Created by 钱逊 on 2017/4/17.
 */
public class CookieUtil {
    private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static void addCookie(HttpServletResponse response, String key, String value){
        Cookie cookie = new Cookie(key,value);
        cookie.setMaxAge(Constant.ExpireTime);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static  void setCurrentUser(HttpServletResponse response,User user){
        String info = null;
        try {
            info = mapper.writeValueAsString(user);
            info = AESHandler.encrypt(info);
            addCookie(response, "passport", info);
        }catch(Exception e){

        }
    }

    public static  User getCurrentUser(HttpServletRequest request){
        User currentUser = (User)request.getAttribute("current_user");
        if( currentUser==null ) {
            try{
                String passportCookie = getCookie(request, "passport");
                if( !passportCookie.trim().equals("") && passportCookie!=null ) {
                    passportCookie = URLDecoder.decode(passportCookie, "UTF-8");
                    passportCookie = AESHandler.decrypt(passportCookie);
                    currentUser = mapper.readValue(passportCookie, User.class);
                    request.setAttribute("current_user", currentUser);
                }
            }catch(Exception e){
                logger.error("can not get user", e);
            }
        }
        return currentUser;
    }

    public static String getCookie(HttpServletRequest request,String key){
        Cookie[] cookies =  request.getCookies();
        if(cookies.length!=0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(key)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void clean(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies =  request.getCookies();
        if(cookies.length!=0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(Constant.ACCESS_TOKEN) || cookie.getName().equals(Constant.PASSPORT)){
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}