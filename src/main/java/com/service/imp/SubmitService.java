package com.service.imp;

import com.dao.ITaskSubmitDao;
import com.pojo.Submit;
import com.pojo.socket.MessageHandler;
import com.service.ISubmitService;
import com.websocket.WebSocketConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
@Service
public class SubmitService  implements ISubmitService {
    @Resource
    ITaskSubmitDao submitDao;


    @Override
    public List<Submit> getSubmitByStudentId(long studentId,long courseId) {
        return submitDao.getByStudentId(studentId,courseId);
    }

    @Override
    public List<Submit> getByExpId(long expId) {
        return submitDao.getByExpId(expId);
    }

    @Override
    public void create(Submit submit) {
        submitDao.create(submit);
    }

    @Override
    public Submit getById(long id) {
        return submitDao.getById(id);
    }

    @Override
    public void update(Submit submit) {
        submitDao.update(submit);
    }

    @Override
    public List<Submit> getNewExp(long userId) {
        return submitDao.getNewExp(userId);
    }

}
