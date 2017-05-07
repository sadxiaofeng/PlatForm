package com.service;

import com.pojo.Classroom;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public interface IClassService {

    List<Classroom> getClasses(long courseId);
}
