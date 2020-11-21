package com.GDMS.entity;

import java.io.Serializable;

public class StudentAccountEntity implements Serializable {
    /**
     * 主键
     */
    private long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
    /**
     * 是否删除了
     */
    private String dr;

    /**
     * 已选id
     */
    private String selectedId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    @Override
    public String toString() {
        return "StudentAccountEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dr='" + dr + '\'' +
                ", selectedId='" + selectedId + '\'' +
                '}';
    }
}
