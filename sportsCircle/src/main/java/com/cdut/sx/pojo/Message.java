package com.cdut.sx.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component("message")
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    @Column(name="messageid")
    private int messageid;//消息自己的ID
    @Column(name="lsex")
    private String lsex;//性别要求
    @Column(name="location")
    private String location;//约定地点
    @Column(name="outDate")
    private Date outDate; //发布时间
    @Column(name="content")
    private String content;//内容
    @Column(name="belongTo")
    private String belongTo;//所属板块
    @Column(name="assignTime")
    private String assignTime;//约定时间
    @OneToMany
    private Set<Comments> comments = new HashSet<Comments>();//一状态有多条评论
    @OneToMany
    private Set<Remind> reminds = new HashSet<>();//一条状态有多个应约
    @JoinColumn(name="userId")
    @ManyToOne(targetEntity = Message.class)
    private String userId;//所属用户的ID
    @Column(name="active")
    private String active;//是否可查看 "active"和“dead” 两种取值

    public Message() {
        super();
    }

    public Message(String lsex, String location, String userId,
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

    public Message(int messageid, String lsex, String location, Date outDate,
                   String content, String belongTo, String assignTime,
                   Set<Comments> comments, String userId, String active) {
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

    public Set<Remind> getReminds() {
        return reminds;
    }

    public void setReminds(Set<Remind> reminds) {
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

    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
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
