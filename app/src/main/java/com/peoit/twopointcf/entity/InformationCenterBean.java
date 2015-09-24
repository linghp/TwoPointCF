package com.peoit.twopointcf.entity;

/**
 * Created by ling on 2015/8/10.
 * last:2015/8/10
 * description:
 */
public class InformationCenterBean {
    private  String time;
    private String title;
    private String content;

    public InformationCenterBean(String time, String title, String content) {
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
