package com.cdut.sx.pojo;

import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Proxy(lazy = false)
@Entity
@Table(name = "circle")
@Component("circle")
public class Circle {
    @Id
    @GeneratedValue
    private int circleId;
    //圈子总人数
    private int userCount;
    //圈子总帖数
    private int messageCount;
    //圈子名字
    private String circleName;
    //圈子的帖子
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Message.class, mappedBy = "belongTo")
    private List<Message> circleMessages = new LinkedList<>();
    //圈子的用户
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = User.class, mappedBy = "circles")
    private Set<User> circleUsers = new HashSet<>();
    //圈子的背景图片
    private String bgImgPath;

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public List<Message> getCircleMessages() {
        return circleMessages;
    }

    public void setCircleMessages(List<Message> circleMessages) {
        this.circleMessages = circleMessages;
    }

    public Set<User> getCircleUsers() {
        return circleUsers;
    }

    public void setCircleUsers(Set<User> circleUsers) {
        this.circleUsers = circleUsers;
    }

    public String getBgImgPath() {
        return bgImgPath;
    }

    public void setBgImgPath(String bgImgPath) {
        this.bgImgPath = bgImgPath;
    }

}

