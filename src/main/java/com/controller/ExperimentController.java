package com.controller;

import com.alibaba.fastjson.JSON;
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
        String expId = request.getParameter("expId");
        if(expId!=null && !expId.trim().equals("")){
            Experiment exp = experimentService.getById(Long.parseLong(expId));
            exp.getDeadLine();
            mv.addObject("exp",exp);
        }
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
        submitList.stream().forEach(submit->{
            if(submit.getIsRead()==0){
                Submit mit = new Submit();
                mit.setId(submit.getId());
                mit.setIsRead(1);
                submitService.update(mit);
            }
        });
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
    @RequestMapping("modifyExp")
    public UniversalResult modifyExp(@RequestParam("id") long id,@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("deadline")String deadline,
                                     @RequestParam("courseId") long courseId,@RequestParam("classId") long classId, HttpServletRequest request){
        User teacher = CookieUtil.getCurrentUser(request);
        Experiment experiment = experimentService.getById(id);
        experiment.setDeadLine(DateUtil.parse(deadline));
        experiment.setCreatorId(teacher.getId());
        experiment.setTitle(title);
        experiment.setCourseId(courseId);
        experiment.setClassId(classId);
        experiment.setContent(content);
        experiment.setCreateTime(new Date());
        experimentService.update(experiment);
        return UniversalResult.createSuccessResult(null);
    }


    @ResponseBody
    @RequestMapping("publishExp")
    public UniversalResult publishExp(long id,long classId){
        experimentService.publishExp(id,classId);
        return UniversalResult.createSuccessResult(null);
    }

    @RequestMapping("editExp")
    public ModelAndView editExp(long courseId,long subId){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","coding/coding");
        mv.addObject("courseId",courseId);
        Submit submit = submitService.getById(subId);
        String content = submit.getContent();
        content.replace("<","&lt;");
        content.replace(">","&gt;");
        submit.setContent(content);
        mv.addObject("submit",submit);
        mv.addObject("parentPages",new String[]{"course"+courseId,"listCourse"});
        return mv;
    }

    @RequestMapping("viewCode")
    public ModelAndView viewCode(long courseId,long subId){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","stu/viewCode");
        mv.addObject("courseId",courseId);
        mv.addObject("submit",submitService.getById(subId));
        mv.addObject("parentPages",new String[]{"course"+courseId,"listCourse"});
        return mv;
    }

    @ResponseBody
    @RequestMapping("delete")
    public UniversalResult delete(long expId){
        experimentService.delete(expId);
        return UniversalResult.createSuccessResult(null);
    }

    @RequestMapping("showDetail")
    public ModelAndView showDetail(long courseId,long expId){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","tea/detail");
        mv.addObject("experiment",experimentService.getById(expId));
        List<Submit> submitList = submitService.getByExpId(expId);
        mv.addObject("submitList",submitList);
        mv.addObject("courseId",courseId);
        mv.addObject("leaf","viewTask"+courseId);
        mv.addObject("parentPages",new String[]{"course"+courseId,"class"+courseId});
        return mv;
    }

    @RequestMapping("checkCode")
    public ModelAndView checkCode(long courseId,long submitId){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","tea/viewCode");
        Submit submit = submitService.getById(submitId);
        mv.addObject("experiment",experimentService.getById(submit.getExperimentId()));
        mv.addObject("submit",submit);
        mv.addObject("leaf","viewTask"+courseId);
        mv.addObject("parentPages",new String[]{"course"+courseId,"class"+courseId});
        return mv;
    }

    @ResponseBody
    @RequestMapping("setGrade")
    public UniversalResult setGrade(long id,String grade){
        Submit submit = submitService.getById(id);
        submit.setGrade(grade);
        submitService.update(submit);
        return UniversalResult.createSuccessResult(null);
    }
}
