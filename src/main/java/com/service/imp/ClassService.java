package com.service.imp;

import com.dao.IClassDao;
import com.pojo.Classroom;
import com.service.IClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
@Service
public class ClassService implements IClassService{
    @Resource
    IClassDao classDao;

    @Override
    public List<Classroom> getClasses(long courseId) {
        return classDao.getClasses(courseId);
    }
}
