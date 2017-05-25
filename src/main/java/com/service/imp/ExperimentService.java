package com.service.imp;

import com.dao.IExperimentDao;
import com.pojo.Experiment;
import com.pojo.Submit;
import com.pojo.User;
import com.service.IExperimentService;
import com.service.ISubmitService;
import com.service.IUserService;
import com.util.CookieUtil;
import com.websocket.WebSocketConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
@Service
public class ExperimentService implements IExperimentService {

    @Resource
    IExperimentDao experimentDao;

    @Resource
    ISubmitService submitService;

    @Resource
    IUserService userService;


    @Override
    public List<Experiment> getExperiment(long courseId, long classId) {
        return experimentDao.getByCoursClass(courseId,classId);
    }

    @Override
    public void createExperiment(Experiment experiment) {
        experimentDao.create(experiment);
    }

    @Override
    public void publishExp(long id,long classId) {
        Experiment experiment = getById(id);
        experiment.setStatus(1l);
        update(experiment);
        List<User> userList = userService.getUserByClass(classId);
        String content = experiment.getContent();
        content = "/*  \n标题："+ experiment.getTitle() + "\n" + "要求：\n" + content + "\n*/";
        for(User user : userList){
            Submit submit = new Submit(user.getId(),experiment.getCourseId(),experiment.getId(),new Date());
            submit.setContent(content);
            submitService.create(submit);
            submit.setExperiment(experiment);
            sendNewSubmitMS(user.getAccount(),submit);
        }
    }

    @Override
    public void delete(long id) {
        experimentDao.delete(id);
    }

    @Override
    public Experiment getById(long id) {
        return experimentDao.getById(id);
    }

    @Override
    public void update(Experiment experiment) {
        experimentDao.update(experiment);
    }

    @Override
    public void sendNewSubmitMS(String account, Submit submit) {
        WebSocketConfig.getMsHandler().sendMessage(account,submit);
    }

}
