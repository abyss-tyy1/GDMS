package com.GDMS.dto;

public class ClassInfoReqDto extends BaseRequestDto{
    /**
     * 教室名字
     */
    private String  className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
