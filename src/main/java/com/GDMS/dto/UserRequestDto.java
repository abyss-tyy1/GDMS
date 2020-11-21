package com.GDMS.dto;

import java.io.Serializable;

public class UserRequestDto implements Serializable {
    private String userName;
    private long userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
