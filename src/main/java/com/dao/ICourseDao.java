package com.dao;

import com.pojo.Classroom;
import com.pojo.Course;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface ICourseDao {
    List<Course> getCoursesByTeacherId(long teacherId);

    Course getById(long id);

    List<Course> getCoursesByClassId(long classId);

    void createCourse(Course course);
}
