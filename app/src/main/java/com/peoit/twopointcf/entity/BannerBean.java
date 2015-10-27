package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by ling on 2015/10/26.
 * description:
 */
public class BannerBean implements Serializable {

    /**
     * id : 23844ed4-30dd-409c-a9a2-5368f510543b
     * name : banner02.jpg
     * title : 测试11
     * path : /upload/15940263-bdad-4f2a-86d1-e5d82be0e9a0.jpg
     * description : 测试11
     * city : null
     * sortOrder : 0
     */

    private String id;
    private String name;
    private String title;
    private String path;
    private String description;
    private String city;
    private String type;
    private String url;
    private int sortOrder;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public int getSortOrder() {
        return sortOrder;
    }
}
