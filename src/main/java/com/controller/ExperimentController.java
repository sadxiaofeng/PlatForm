package com.controller;

import com.dao.IUserDao;
import com.pojo.Experiment;
import com.pojo.Submit;
import com.pojo.User;
import com.service.*;
import com.util.CookieUtil;
import com.util.DateUtil;
import com.util.UniversalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
@RequestMapping("exp")
@Controller
public class ExperimentController {
    private static Logger logger = LoggerFactory.getLogger(ExperimentController.class);

    @Resource
    IExperimentService experimentService;

    @Resource
    IClassService classService;



    @Resource
    ISubmitService submitService;

    @Resource
    IUserService userService;

    @RequestMapping("addExp")
    public ModelAndView addExp(HttpServletRequest request,int courseId,int classId){
        ModelAndView mv = new ModelAndView("main");
        User user = CookieUtil.getCurrentUser(request);
        mv.addObject("page","tea/addTask");
        mv.addObject("courseId",courseId);
        mv.addObject("classId",classId);
        mv.addObject("leaf","addTask"+courseId);
        mv.addObject("parentPages",new String[]{"course"+courseId,"class"+courseId});
        return mv;
    }

    @RequestMapping("viewTask")
    public ModelAndView viewTask(int courseId,int classId){
        ModelAndView mv = new ModelAndView("main");
        List<Experiment> experimentList =experimentService.getExperiment(courseId,classId);
        mv.addObject("page","tea/view");
        mv.addObject("courseId",courseId);
        mv.addObject("classId",classId);
        mv.addObject("experimentList",experimentList);
        mv.addObject("leaf","viewTask"+courseId);
        mv.addObject("parentPages",new String[]{"course"+courseId,"class"+courseId});
        return mv;
    }

    @RequestMapping("listView")
    public ModelAndView listView(int courseId, HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("main");
        User user = CookieUtil.getCurrentUser(request);
        List<Submit> submitList = submitService.getSubmitByStudentId(user.getId(),courseId);
        mv.addObject("page","stu/view");
        mv.addObject("courseId",courseId);
        mv.addObject("submitList",submitList);
        mv.addObject("parentPages",new String[]{"course"+courseId,"listCourse"});
        return mv;
    }

    @ResponseBody
    @RequestMapping("createExp")
    public UniversalResult createExp(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("deadline")String deadline,
                                     @RequestParam("courseId") long courseId,@RequestParam("classId") long classId, HttpServletRequest request){
        User teacher = CookieUtil.getCurrentUser(request);
        Experiment experiment = new Experiment();
        experiment.setDeadLine(DateUtil.parse(deadline));
        experiment.setCreatorId(teacher.getId());
        experiment.setTitle(title);
        experiment.setCourseId(courseId);
        experiment.setClassId(classId);
        experiment.setContent(content);
        experiment.setCreateTime(new Date());
        experimentService.createExperiment(experiment);
        return UniversalResult.createSuccessResult(null);
    }

    @ResponseBody
    @RequestMapping("publishExp")
    public UniversalResult publishExp(long id,long classId){
        experimentService.publishExp(id);
        Experiment experiment = experimentService.getById(id);
        List<User> userList = userService.getUserByClass(classId);
        for(User user : userList){
            Submit submit = new Submit(user.getId(),experiment.getCourseId(),experiment.getId(),new Date());
            submitService.create(submit);
        }
        return UniversalResult.createSuccessResult(null);
    }

    @RequestMapping("editExp")
    public ModelAndView editExp(){
        ModelAndView mv = new ModelAndView("business/coding/coding");
        return mv;
    }
}
