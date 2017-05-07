package com.service.imp;

import com.dao.IExperimentDao;
import com.pojo.Experiment;
import com.service.IExperimentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
@Service
public class ExperimentService implements IExperimentService{

    @Resource
    IExperimentDao experimentDao;

    @Override
    public List<Experiment> getExperiment(long courseId, long classId) {
        return experimentDao.getByCoursClass(courseId,classId);
    }

    @Override
    public void createExperiment(Experiment experiment) {
        experimentDao.create(experiment);
    }

    @Override
    public void publishExp(long id) {
        experimentDao.publishExp(id);
    }

    @Override
    public void delete(long id) {
        experimentDao.delete(id);
    }

    @Override
    public Experiment getById(long id) {
        return experimentDao.getById(id);
    }
}
