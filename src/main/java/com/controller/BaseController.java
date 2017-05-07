package com.controller;

import com.pojo.User;
import com.service.IUserService;
import com.util.CookieUtil;
import com.util.UniversalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 钱逊 on 2017/4/16.
 */
@RequestMapping("/")
@Controller
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    IUserService userService;

    @RequestMapping("home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","home");
        return mv;
    }

    @RequestMapping("/")
    public String defaultMain(){
        return "redirect:home";
    }

    @RequestMapping("changePass")
    public ModelAndView changePass(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","admin/changePass");
        mv.addObject("parentPages",new String[]{"accout","changePass"});
        return mv;
    }

    @ResponseBody
    @RequestMapping("checkPwd")
    public UniversalResult checkPwd(HttpServletRequest request,String old,String pwd){
        User user = CookieUtil.getCurrentUser(request);
        if(user.getPassword().equals(old)){
            user.setPassword(pwd);
            userService.update(user);
            return UniversalResult.createSuccessResult(null);
        }else{
            return UniversalResult.createErrorResult(500);
        }
    }
}
