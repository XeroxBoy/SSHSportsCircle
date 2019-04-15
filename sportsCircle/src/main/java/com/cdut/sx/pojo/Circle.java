package com.cdut.sx.pojo;

import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Proxy(lazy = false)
@Entity
@Table(name = "circle")
@Component("Circle")
public class Circle {
    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    @Id
    @GeneratedValue
    @Column(name = "circle_id")
    private int circleId;
    //圈子总人数
    @Column(name = "user_count")
    private int userCount;
    //圈子总帖数
    @Column(name = "message_count")
    private int messageCount;
    //圈子名字
    @Column(name = "circle_name")
    private String circleName;
    //圈子的帖子
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Message.class, mappedBy = "belongTo")
    private List<Message> circleMessages = new ArrayList<>();
    //圈子的用户
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = User.class, mappedBy = "circles")
    private List<User> circleUsers = new ArrayList<>();




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
        this.circleMessages = (List<Message>) circleMessages;
    }

    public List<User> getCircleUsers() {
        return circleUsers;
    }

    public void setCircleUsers(List<User> circleUsers) {
        this.circleUsers = circleUsers;
    }



    @Override
    public String toString() {
        return "Circle{" +
                "circleId=" + circleId +
                ", userCount=" + userCount +
                ", messageCount=" + messageCount +
                ", circleName='" + circleName + '\'' +
                ", circleMessages=" + circleMessages +
                ", circleUsers=" + circleUsers + '\'' +
                '}';
    }
}

