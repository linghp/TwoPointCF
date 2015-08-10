package com.peoit.twopointcf.entity;

/**
 * Created by ling on 2015/8/10.
 * last:2015/8/10
 * description:
 */
public class BusinessDynamicsBean {
    public static final int ITEM = 0;
    public static final int SECTION = 1;
    private  int type;
    private  String time;
    private String title;
    private String content;

    public BusinessDynamicsBean(int type, String time, String title, String content) {
        this.type = type;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "BusinessDynamicsBean{" +
                "type=" + type +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
