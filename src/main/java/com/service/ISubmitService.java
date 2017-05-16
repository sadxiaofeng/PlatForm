package com.service;

import com.pojo.Submit;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface ISubmitService {
    List<Submit> getSubmitByStudentId(long studentId,long courseId);

    void create(Submit submit);

    Submit getById(long id);

    void update(Submit submit);
}
