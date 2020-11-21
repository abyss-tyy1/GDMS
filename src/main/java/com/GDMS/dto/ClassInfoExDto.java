package com.GDMS.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.sql.Timestamp;

public class ClassInfoExDto implements Serializable {
    /**
     * 行号
     */
    @Excel(name = "行号", orderNum = "1", width = 25)
    private Integer num;

    /**
     * 教室名称
     */
    @Excel(name = "教室名称", orderNum = "2", width = 25)
    private String className;

    /**
     * 总座位数
     */
    @Excel(name = "总座位数", orderNum = "3", width = 25)
    private Integer allSeatCount;

    /**
     * 剩余座位数
     */
    @Excel(name = "剩余座位数", orderNum = "4", width = 25)
    private Integer restSeatCount;

    /**
     * 教室照片
     */
    @Excel(name = "教室照片", orderNum = "5", width = 25)
    private String classPhoto;

    /**
     * 简介
     */
    @Excel(name = "简介", orderNum = "6", width = 25)
    private String briefIntroduction;

    /**
     *添加时间
     */
    @Excel(name = "添加时间", orderNum = "7", width = 25)
    private Timestamp addTime;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
        return "ClassInfoExDto{" +
                "num=" + num +
                ", className='" + className + '\'' +
                ", allSeatCount=" + allSeatCount +
                ", restSeatCount=" + restSeatCount +
                ", classPhoto='" + classPhoto + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
