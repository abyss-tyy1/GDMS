package com.GDMS.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class StudentResDto implements Serializable {
private String studentName;
private Integer age;
//1启用， 0禁用
private Integer status;
private  String hobby;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this.getClass());
    }
}
