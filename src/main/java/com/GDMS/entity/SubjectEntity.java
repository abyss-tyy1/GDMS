package com.GDMS.entity;

import java.io.Serializable;

public class SubjectEntity implements Serializable {

    /**
     * 主键
     */
    private long id;

    /**
     * 指导老师
     */
    private String instuctTeacher;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目类型
     */
    private String subjectType;

    /**
     * 科研 0否，1是
     */
    private int scientificResearch;

    /**
     * 毕业年份--（届）
     */
    private String graduationYear;

    /**
     * 已选人数
     */
    private String selectedNumber;

    /**
     * 状态： 0可选，1选题完成
     */
    private int status;

    /**
     * 0使用中，1删除
     */
    private int dr ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstuctTeacher() {
        return instuctTeacher;
    }

    public void setInstuctTeacher(String instuctTeacher) {
        this.instuctTeacher = instuctTeacher;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public int getScientificResearch() {
        return scientificResearch;
    }

    public void setScientificResearch(int scientificResearch) {
        this.scientificResearch = scientificResearch;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getSelectedNumber() {
        return selectedNumber;
    }

    public void setSelectedNumber(String selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
