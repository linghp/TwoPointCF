package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by zyz on 2015/10/15.
 */
public class IsConcernBean implements Serializable {

    /**
     * concern : y
     */

    private String concern;

    public void setConcern(String concern) {
        this.concern = concern;
    }

    public String getConcern() {
        return concern;
    }

    @Override
    public String toString() {
        return "IsConcernBean{" +
                "concern='" + concern + '\'' +
                '}';
    }
}
