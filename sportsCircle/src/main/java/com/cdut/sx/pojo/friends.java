package com.cdut.sx.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component("friends")
@Table(name="friends")
public class friends {
    @Id
    @OneToOne
    @GeneratedValue
    private int friendsId;//主键
    @Column
    private String friendsFrom;//好友1方
    @Column
    private String friendsTo;//好友另一方

    public int getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(int friendsId) {
        this.friendsId = friendsId;
    }

    public String getFriendsFrom() {
        return friendsFrom;
    }

    public void setFriendsFrom(String friendsFrom) {
        this.friendsFrom = friendsFrom;
    }

    public String getFriendsTo() {
        return friendsTo;
    }

    public void setFriendsTo(String friendsTo) {
        this.friendsTo = friendsTo;
    }


}