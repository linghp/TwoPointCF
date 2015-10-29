package com.peoit.twopointcf.entity;

import java.io.Serializable;

/**
 * Created by ling on 2015/9/1.
 * description:
 */
public class ProjectAnnouncementBean implements Serializable{

    /**
     * id : 6f254075-6f17-4243-a513-3bdc20aab417
     * projectName : 测试三
     * noticeTitle : 标题
     * noticeContent : 内容
     * userName : 123
     * projectId : 151021035167
     * userId : 3edb1bc9-0266-4786-b651-cdbff38a8fa8
     * createData : 2015-10-28
     */

    private String id;
    private String projectName;
    private String noticeTitle;
    private String noticeContent;
    private String userName;
    private String projectId;
    private String userId;
    private String createData;

    public void setId(String id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public String getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public String getUserName() {
        return userName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreateData() {
        return createData;
    }

    @Override
    public String toString() {
        return "ProjectAnnouncementBean{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", userName='" + userName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                ", createData='" + createData + '\'' +
                '}';
    }
}
