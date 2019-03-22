package com.cdut.sx.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class message {
    private int messageid;//消息自己的ID
    private String lsex;//性别要求
    private String location;//约定地点
    private Date outDate; //发布时间
    private String content;//内容
    private String belongTo;//所属板块
    private String assignTime;//约定时间
    private Set<comments> comments = new HashSet<comments>();//一状态有多条评论
    private Set<remind> reminds = new HashSet<>();//一条状态有多个应约
    private String userId;//所属用户的ID
    private String active;//是否可查看 "active"和“dead” 两种取值

    public message() {
        super();
    }

    public message(String lsex, String location, String userId,
                   Date outDate, String content, String belongTo, String assignTime) {
        super();

        this.lsex = lsex;
        this.location = location;
        this.userId = userId;
        this.outDate = outDate;
        this.content = content;
        this.belongTo = belongTo;
        this.assignTime = assignTime;
        this.active = "active";
    }

    public message(int messageid, String lsex, String location, Date outDate,
                   String content, String belongTo, String assignTime,
                   Set<comments> comments, String userId, String active) {
        super();
        this.messageid = messageid;
        this.lsex = lsex;
        this.location = location;
        this.outDate = outDate;
        this.content = content;
        this.belongTo = belongTo;
        this.assignTime = assignTime;
        this.comments = comments;
        this.userId = userId;
        this.active = active;
    }

    public Set<remind> getReminds() {
        return reminds;
    }

    public void setReminds(Set<remind> reminds) {
        this.reminds = reminds;
    }

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    @Override
    public String toString() {
        return "message [" + "outDate=" + outDate + ", content="
                + content + ", belongTo=" + belongTo + ", assignTime=" + assignTime
                + "]";
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public Set<comments> getComments() {
        return comments;
    }

    public void setComments(Set<comments> comments) {
        this.comments = comments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLsex() {
        return lsex;
    }

    public void setLsex(String lsex) {
        this.lsex = lsex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }


}
