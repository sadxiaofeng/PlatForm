package com.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public class Submit {
    private Long id;
    private Long studentId;
    private Long courseId;
    private Course course;
    private Date submitTime;
    private Long status;
    private Long experimentId;
    private String content;
    private Date createTime;
    private Experiment experiment;

    public Submit() {
    }

    public Submit(Long studentId, Long courseId, Long experimentId, Date createTime) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.experimentId = experimentId;
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
