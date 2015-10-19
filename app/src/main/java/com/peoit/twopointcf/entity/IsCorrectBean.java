package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by zyz on 2015/10/15.
 */
public class IsCorrectBean implements Serializable {

    /**
     * isCorrect : true
     */

    private boolean isCorrect;

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }
}
