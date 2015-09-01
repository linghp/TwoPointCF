package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by ling on 2015/9/1.
 * description:
 */
public class ProjectAnnouncementBean implements Serializable{
    private String content;
    private String time;

    public ProjectAnnouncementBean(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ProjectAnnouncementBean{" +
                "content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
