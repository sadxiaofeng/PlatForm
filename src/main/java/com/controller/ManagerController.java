package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Classroom;
import com.pojo.Course;
import com.pojo.User;
import com.service.IClassService;
import com.service.ICourseService;
import com.service.IUserService;
import com.service.imp.CourseService;
import com.service.imp.UserService;
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
import java.util.Iterator;

/**
 * Created by 钱逊 on 2017/5/3.
 */
@RequestMapping("admin")
@Controller
public class ManagerController {

    private static Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Resource
    IUserService userService;

    @Resource
    IClassService classService;

    @Resource
    ICourseService courseService;

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
    public UniversalResult createStudent(String students,long classId){
        JSONArray array = JSON.parseArray(students);
        if(classService.getById(classId)==null){
            return UniversalResult.createErrorResult(404);
        }
        for(Iterator it = array.iterator();it.hasNext();){
            JSONObject obj = (JSONObject) it.next();
            String id = obj.getString("id");
            String name = obj.getString("name");
            User user = new User(id,id,1,name,classId);
            userService.createUser(user);
        }
        return UniversalResult.createSuccessResult(null);
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

    @RequestMapping("resetPassword")
    public ModelAndView resetPassword(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","admin/resetPassword");
        mv.addObject("parentPages",new String[]{"courseAdmin","resetPassword"});
        return mv;
    }

    @ResponseBody
    @RequestMapping("resetPwd")
    public UniversalResult  resetPwd(String name,String jobName){
        User user = userService.getUserByAccount(jobName);
        if(user.getName().equals(name)){
            user.setPassword(user.getAccount());
            userService.update(user);
            return UniversalResult.createSuccessResult(null);
        }else{
            return UniversalResult.createErrorResult(400);
        }
    }


    @RequestMapping("addClass")
    public ModelAndView addClass(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","admin/addClass");
        mv.addObject("parentPages",new String[]{"class","addClass"});
        return mv;
    }

    @ResponseBody
    @RequestMapping("createClass")
    public UniversalResult createClass(long id,String name){
        if(classService.getById(id)==null){
            Classroom classroom = new Classroom(id,name);
            classService.createClass(classroom);
            return UniversalResult.createSuccessResult(null);
        }else{
            return UniversalResult.createErrorResult(202);
        }
    }

    @RequestMapping("addCourse")
    public ModelAndView addCourse(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","admin/addCourse");
        mv.addObject("parentPages",new String[]{"courseAdmin","addCourse"});
        return mv;
    }

    @ResponseBody
    @RequestMapping("createCourse")
    public UniversalResult createCourse(String name,String jobNumber,int type){
        if(userService.getUserByAccount(jobNumber)==null){
            return UniversalResult.createErrorResult(404);
        }else{
            Course course = new Course(name,jobNumber,type);
            courseService.createCourse(course);
            return UniversalResult.createSuccessResult(null);
        }
    }

    @RequestMapping("allocation")
    public ModelAndView allocation(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","admin/allocation");
        mv.addObject("parentPages",new String[]{"courseAdmin","allocation"});
        return mv;
    }

}
