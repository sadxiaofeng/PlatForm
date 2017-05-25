package com.service;

import com.pojo.Experiment;
import com.pojo.Submit;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface ISubmitService {
    //学生查看本课程的所有实验
    List<Submit> getSubmitByStudentId(long studentId,long courseId);
    //老师查询本实验的所有学生完成情况
    List<Submit> getByExpId(long expId);

    void create(Submit submit);

    Submit getById(long id);

    void update(Submit submit);

    List<Submit> getNewExp(long userId);
}
