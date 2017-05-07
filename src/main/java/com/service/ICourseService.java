package com.service;

import com.pojo.Course;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface ICourseService {

    public List<Course> getCourseByStudentClassId(long id);

    public List<Course> getCourseByTeacherId(long id);
}
