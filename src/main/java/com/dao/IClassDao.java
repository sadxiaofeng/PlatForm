package com.dao;

import com.pojo.Classroom;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface IClassDao {
    List<Classroom> getClasses(long courseId);

    Classroom getById(long classId);

    void createClass(Classroom classroom);
}
