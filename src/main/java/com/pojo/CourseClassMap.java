package com.pojo;

/**
 * Created by 钱逊 on 2017/5/30.
 */
public class CourseClassMap {
    Long courseId;
    Long classId;

    public CourseClassMap() {
    }

    public CourseClassMap(Long courseId, Long classId) {
        this.courseId = courseId;
        this.classId = classId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
