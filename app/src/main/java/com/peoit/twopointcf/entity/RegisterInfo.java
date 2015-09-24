package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * 注册
 * Created by zyz on 2015/9/24.
 */
public class RegisterInfo implements Serializable{
    /**
     * userName : apple
     * phoneNumber : 12345678901
     * password : 123456
     * authorizationCode : 123456
     * verifyCode : 123456
     */

    private String userName;
    private String phoneNumber;
    private String password;
    private String authorizationCode;
    private String verifyCode;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
