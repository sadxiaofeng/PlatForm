package com.controller;

import com.pojo.User;
import com.service.IUserService;
import com.util.UniversalResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by 钱逊 on 2017/5/3.
 */
@RequestMapping("admin")
@Controller
public class ManagerController {

    @Resource
    IUserService userService;

    @RequestMapping("addStudent")
    public ModelAndView addStudent(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","admin/addStudent");
        mv.addObject("parentPages",new String[]{"user","addStudent"});
        return mv;
    }

    @RequestMapping("addTeacher")
    public ModelAndView addTeacher(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","admin/addTeacher");
        mv.addObject("parentPages",new String[]{"user","addTeacher"});
        return mv;
    }

    @ResponseBody
    @RequestMapping("createStudent")
    public UniversalResult createStudent(String users,long classId){
        return null;
    }

    @ResponseBody
    @RequestMapping("createTeacher")
    public UniversalResult createTeacher(String name,String jobNumber){
        User user = new User();
        user.setIdentity(2);
        user.setAccount(jobNumber);
        user.setPassword(jobNumber);
        user.setName(name);
        userService.createUser(user);
        return UniversalResult.createSuccessResult(null);
    }

    @ResponseBody
    @RequestMapping("resetPassword")
    public UniversalResult resetPassword(String name,String jobNumber){
        return null;
    }
}
