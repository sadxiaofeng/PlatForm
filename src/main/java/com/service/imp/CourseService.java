package com.service.imp;

import com.dao.ICourseDao;
import com.pojo.Course;
import com.service.IClassService;
import com.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
@Service
public class CourseService implements ICourseService{

    @Resource
    ICourseDao courseDao;

    @Override
    public List<Course> getCourseByStudentClassId(long id){
        return courseDao.getCoursesByClassId(id);
    }

    @Override
    public List<Course> getCourseByTeacherId(long id){
        return courseDao.getCoursesByTeacherId(id);
    }
}
