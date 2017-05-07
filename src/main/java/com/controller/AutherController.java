package com.controller;

import com.pojo.User;
import com.service.IUserService;
import com.util.Constant;
import com.util.CookieUtil;
import com.util.TokenUtil;
import com.util.UniversalResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 钱逊 on 2017/4/15.
 */
@RequestMapping("auth")
@Controller
public class AutherController {
    @Resource
    IUserService userService;

    @RequestMapping("login")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @ResponseBody
    @RequestMapping("accessToken")
    public UniversalResult accessToken(@RequestParam("account") String account,@RequestParam("password") String password, HttpServletResponse response){
        User user = userService.getUserByAccount(account);
        CookieUtil.setCurrentUser(response,user);
        if(user.getPassword().equals(password)){
            Cookie cookie = new Cookie(Constant.ACCESS_TOKEN, TokenUtil.getAccessToken());
            cookie.setMaxAge(Constant.ExpireTime);
            cookie.setPath("/");
            response.addCookie(cookie);
            return UniversalResult.createSuccessResult(null);
        }
        return UniversalResult.createErrorResult(404);
    }

    @RequestMapping("loginout")
    public String loginout(HttpServletRequest request,HttpServletResponse response){
        CookieUtil.clean(request,response);
        return "redirect:/mvc/login";
    }
}
