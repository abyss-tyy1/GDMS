package com.GDMS.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ClassInfoEntity implements Serializable {
    /**
     * 主键
     */
    private long id;

    /**
     * 教室名称
     */
    private String className;

    /**
     * 总座位数
     */
    private Integer allSeatCount;

    /**
     * 剩余座位数
     */
    private Integer restSeatCount;

    /**
     * 教室照片
     */
    private String classPhoto;

    /**
     * 简介
     */
    private String briefIntroduction;

    /**
     *添加时间
     */
    private Timestamp addTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getAllSeatCount() {
        return allSeatCount;
    }

    public void setAllSeatCount(Integer allSeatCount) {
        this.allSeatCount = allSeatCount;
    }

    public Integer getRestSeatCount() {
        return restSeatCount;
    }

    public void setRestSeatCount(Integer restSeatCount) {
        this.restSeatCount = restSeatCount;
    }

    public String getClassPhoto() {
        return classPhoto;
    }

    public void setClassPhoto(String classPhoto) {
        this.classPhoto = classPhoto;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "ClassInfoEntity{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", allSeatCount=" + allSeatCount +
                ", restSeatCount=" + restSeatCount +
                ", classPhoto='" + classPhoto + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
