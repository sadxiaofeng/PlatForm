package com.controller;

import com.alibaba.fastjson.JSON;
import com.complie.DynamicEngine;
import com.dao.IUserDao;
import com.pojo.Classroom;
import com.pojo.Course;
import com.pojo.Submit;
import com.pojo.User;
import com.service.*;
import com.service.imp.*;
import com.util.CookieUtil;
import com.util.DateUtil;
import com.util.UniversalResult;
import com.websocket.WebSocketConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.server.RequestUpgradeStrategy;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 钱逊 on 2017/5/12.
 */
@RequestMapping("api")
@Controller
public class APIController {

    @Resource
    IExperimentService experimentService;

    @Resource
    ISubmitService submitService;

    @Resource
    IClassService classService;

    @Resource
    IUserService userService;

    @Resource
    ICourseService courseService;

    @ResponseBody
    @RequestMapping("getNewExp")
    public UniversalResult getNewExp(HttpServletRequest request){
        User user = CookieUtil.getCurrentUser(request);
        return UniversalResult.createSuccessResult(JSON.toJSONString(submitService.getNewExp(user.getId())));
    }

    @ResponseBody
    @RequestMapping("runningCode")
    public UniversalResult runningCode(long id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Submit submit = submitService.getById(id);
        String code = submit.getContent();
        String className = DateUtil.parseClassName(code);
        String result = (String) DynamicEngine.getInstance().javaCodeToObject(className,code);
        return UniversalResult.createSuccessResult(result);
    }

    @ResponseBody
    @RequestMapping("onlineRunning")
    public UniversalResult onlineRunning(String id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String code = WebSocketConfig.getSubmitHandler().getCode(id);
        String className = DateUtil.parseClassName(code);
        String result = (String) DynamicEngine.getInstance().javaCodeToObject(className,code);
        return UniversalResult.createSuccessResult(result);
    }


    @ResponseBody
    @RequestMapping("getContacts")
    public UniversalResult getContacts(HttpServletRequest request){
        User user = CookieUtil.getCurrentUser(request);
        List<User> userlist = userService.getUserByClass(user.getClassId());
        List<Course> courseList = courseService.getCourseByStudentClassId(user.getClassId());
        Set<User> teachers = new HashSet<>();
        for(Course course:courseList){
            teachers.add(course.getTeacher());
        }
        userlist.addAll(teachers);
        for(int i=0;i<userlist.size();i++){
            User temp = userlist.get(i);
            if(temp.getId()==user.getId()){
                userlist.remove(i);
                break;
            }
        }
        return UniversalResult.createSuccessResult(JSON.toJSONString(userlist));
    }
}
