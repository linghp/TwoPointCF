package com.peoit.twopointcf.entity;

/**
 * Created by ling on 2015/8/11.
 * last:2015/8/11
 * description:
 */
public class InvestedProjectBean {
    private  int picture;
    private  int picture_tag;
    private  String time;
    private String title;
    private String money;
    private String content;

    public InvestedProjectBean(int picture, String time, String title, String money) {
        this.picture = picture;
        this.time = time;
        this.title = title;
        this.money = money;
    }

    public InvestedProjectBean(int picture, int picture_tag, String title, String content) {
        this.picture = picture;
        this.picture_tag = picture_tag;
        this.title = title;
        this.content = content;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getPicture_tag() {
        return picture_tag;
    }

    public void setPicture_tag(int picture_tag) {
        this.picture_tag = picture_tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "InvestedProjectBean{" +
                "picture=" + picture +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
