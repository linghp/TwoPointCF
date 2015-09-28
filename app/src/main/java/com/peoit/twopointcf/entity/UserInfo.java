package com.peoit.twopointcf.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * zyz:这些字段是接口文档上的，信息不全，重用的RegisterBean类。注册返回数据和登录返回数据一样的
 * Created by ling on 2015/9/25.
 * description:
 */
public class UserInfo implements Serializable{
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("avatar")
        @Expose
        private String avatar;
        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("isRealNameValidated")
        @Expose
        private String isRealNameValidated;
        @SerializedName("userRealName")
        @Expose
        private String userRealName;

        /**
         *
         * @return
         * The userId
         */
        public Integer getUserId() {
            return userId;
        }

        /**
         *
         * @param userId
         * The userId
         */
        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        /**
         *
         * @return
         * The userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         *
         * @param userName
         * The userName
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         *
         * @return
         * The avatar
         */
        public String getAvatar() {
            return avatar;
        }

        /**
         *
         * @param avatar
         * The avatar
         */
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        /**
         *
         * @return
         * The level
         */
        public String getLevel() {
            return level;
        }

        /**
         *
         * @param level
         * The level
         */
        public void setLevel(String level) {
            this.level = level;
        }

        /**
         *
         * @return
         * The phoneNumber
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         *
         * @param phoneNumber
         * The phoneNumber
         */
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        /**
         *
         * @return
         * The email
         */
        public String getEmail() {
            return email;
        }

        /**
         *
         * @param email
         * The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         *
         * @return
         * The isRealNameValidated
         */
        public String getIsRealNameValidated() {
            return isRealNameValidated;
        }

        /**
         *
         * @param isRealNameValidated
         * The isRealNameValidated
         */
        public void setIsRealNameValidated(String isRealNameValidated) {
            this.isRealNameValidated = isRealNameValidated;
        }

        /**
         *
         * @return
         * The userRealName
         */
        public String getUserRealName() {
            return userRealName;
        }

        /**
         *
         * @param userRealName
         * The userRealName
         */
        public void setUserRealName(String userRealName) {
            this.userRealName = userRealName;
        }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", level='" + level + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", isRealNameValidated='" + isRealNameValidated + '\'' +
                ", userRealName='" + userRealName + '\'' +
                '}';
    }
}
