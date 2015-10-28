package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by ling on 2015/10/28.
 * description:
 */
public class CommentBean implements Serializable{
    /**
     * id : null
     * discussantName : ling
     * comments : ssss
     * avatar : null
     */

    private Object id;
    private String discussantName;
    private String comments;
    private Object avatar;

    public void setId(Object id) {
        this.id = id;
    }

    public void setDiscussantName(String discussantName) {
        this.discussantName = discussantName;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Object getId() {
        return id;
    }

    public String getDiscussantName() {
        return discussantName;
    }

    public String getComments() {
        return comments;
    }

    public Object getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "id=" + id +
                ", discussantName='" + discussantName + '\'' +
                ", comments='" + comments + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
