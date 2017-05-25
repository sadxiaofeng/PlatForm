package com.dao;

import com.pojo.Experiment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface IExperimentDao {
    void create(Experiment experiment);

    void delete(long id);

    void update(Experiment experiment);

    Experiment getById(long id);

    List<Experiment> getByCoursClass(@Param("courseId") long courseId,@Param("classId") long classId);

    void publishExp(long id);


}
