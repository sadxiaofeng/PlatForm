package com.service.imp;

import com.dao.ICourseClassMapDao;
import com.pojo.CourseClassMap;
import com.service.ICourseClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 钱逊 on 2017/5/30.
 */
@Service
public class CourseClassService implements ICourseClassService {
    @Resource
    ICourseClassMapDao CourseClassMapDao;

    @Override
    public void create(CourseClassMap map) {
        CourseClassMapDao.create(map);
    }
}
