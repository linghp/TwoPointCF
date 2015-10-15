package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by zyz on 2015/10/15.
 */
public class IsVerifiedBean implements Serializable {

    /**
     * isVerified : w
     */

    private String isVerified;

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getIsVerified() {
        return isVerified;
    }

    @Override
    public String toString() {
        return "IsVerifiedBean{" +
                "isVerified='" + isVerified + '\'' +
                '}';
    }
}
