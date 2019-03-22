package com.cdut.sx.pojo;

public class friends {
    private int friendsId;//主键
    private String friendsFrom;//好友1方
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