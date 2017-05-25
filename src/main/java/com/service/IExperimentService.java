package com.service;

import com.pojo.Experiment;
import com.pojo.Submit;
import com.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface IExperimentService {
    List<Experiment> getExperiment(long courseId,long classId);

    void createExperiment(Experiment experiment);

    void publishExp(long id,long classId);

    void delete(long id);

    Experiment getById(long id);

    void update(Experiment experiment);

    void sendNewSubmitMS(String account, Submit submit);
}
