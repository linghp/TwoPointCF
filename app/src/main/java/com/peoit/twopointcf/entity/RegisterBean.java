package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by zyz on 2015/9/24.
 */
public class RegisterBean implements Serializable {

    /**
     * userId : 0
     * userName : apple
     * avatar : upload/aaasdas.jpg
     * level : 2
     * phoneNumber : 123456789
     * email : qwretyu@123.com
     * isRealNameValidated : 1
     * userRealName : jack
     */

    private int userId;
    private String userName;
    private String avatar;
    private String level;
    private String phoneNumber;
    private String email;
    private String isRealNameValidated;
    private String userRealName;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsRealNameValidated(String isRealNameValidated) {
        this.isRealNameValidated = isRealNameValidated;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLevel() {
        return level;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getIsRealNameValidated() {
        return isRealNameValidated;
    }

    public String getUserRealName() {
        return userRealName;
    }
}
