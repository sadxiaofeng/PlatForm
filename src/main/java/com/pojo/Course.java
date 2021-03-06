package com.pojo;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public class Course {
    private Long id;
    private String name;
    private String teacherId;
    private User teacher;
    private Integer type;
    private List<Classroom> classroomList;

    public Course() {
    }

    public Course(String name, String teacherId, Integer type) {
        this.name = name;
        this.teacherId = teacherId;
        this.type = type;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    public void setClassroomList(List<Classroom> classroomList) {
        this.classroomList = classroomList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
