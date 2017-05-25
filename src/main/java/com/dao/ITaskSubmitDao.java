package com.dao;

import com.pojo.Experiment;
import com.pojo.Submit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/17.
 */
public interface ITaskSubmitDao {
    void create(Submit submitTask);

    void update(Submit submitTask);

    void delete(int id);

    List<Submit> getByStudentId(@Param("studentId") long studentId,@Param("courseId") long courseId);

    Submit getById(long id);

    List<Submit> getByExpId(long expId);

    List<Submit> getNewExp(long userId);
}

