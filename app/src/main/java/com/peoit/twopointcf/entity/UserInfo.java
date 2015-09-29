package com.peoit.twopointcf.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * zyz:这些字段是接口文档上的，信息不全，重用的RegisterBean类。注册返回数据和登录返回数据一样的
 * Created by ling on 2015/9/25.
 * description: 目前和RegisterBean一样
 */
public class UserInfo implements Serializable{
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("authorizationCode")
        @Expose
        private String authorizationCode;
        @SerializedName("avatar")
        @Expose
        private Object avatar;
        @SerializedName("level")
        @Expose
        private Object level;
        @SerializedName("userCaption")
        @Expose
        private Object userCaption;
        @SerializedName("isRealNameValidated")
        @Expose
        private Object isRealNameValidated;
        @SerializedName("userRealName")
        @Expose
        private Object userRealName;
        @SerializedName("verifyCode")
        @Expose
        private Object verifyCode;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getAuthorizationCode() {
                return authorizationCode;
        }

        public void setAuthorizationCode(String authorizationCode) {
                this.authorizationCode = authorizationCode;
        }

        public Object getAvatar() {
                return avatar;
        }

        public void setAvatar(Object avatar) {
                this.avatar = avatar;
        }

        public Object getLevel() {
                return level;
        }

        public void setLevel(Object level) {
                this.level = level;
        }

        public Object getUserCaption() {
                return userCaption;
        }

        public void setUserCaption(Object userCaption) {
                this.userCaption = userCaption;
        }

        public Object getIsRealNameValidated() {
                return isRealNameValidated;
        }

        public void setIsRealNameValidated(Object isRealNameValidated) {
                this.isRealNameValidated = isRealNameValidated;
        }

        public Object getUserRealName() {
                return userRealName;
        }

        public void setUserRealName(Object userRealName) {
                this.userRealName = userRealName;
        }

        public Object getVerifyCode() {
                return verifyCode;
        }

        public void setVerifyCode(Object verifyCode) {
                this.verifyCode = verifyCode;
        }

        @Override
        public String toString() {
                return "RegisterBean{" +
                        "id='" + id + '\'' +
                        ", userName='" + userName + '\'' +
                        ", email='" + email + '\'' +
                        ", phoneNumber='" + phoneNumber + '\'' +
                        ", password='" + password + '\'' +
                        ", authorizationCode='" + authorizationCode + '\'' +
                        ", avatar=" + avatar +
                        ", level=" + level +
                        ", userCaption=" + userCaption +
                        ", isRealNameValidated=" + isRealNameValidated +
                        ", userRealName=" + userRealName +
                        ", verifyCode=" + verifyCode +
                        '}';
        }
}
