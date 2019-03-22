package com.cdut.sx.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class comments {
    private int commentId;//评论的ID 主键
    private String userId;//发布人
    private String outTime;//发布时间
    private int messagebelongTo;//所属状态,外键
    private String contents;//内容
    private String active;//是否可见

    public comments() {
        super();
    }

    public comments(int commentId, String userId, String outTime,
                    int messagebelongTo, String contents) {
        super();
        this.commentId = commentId;
        this.userId = userId;
        this.outTime = outTime;
        this.messagebelongTo = messagebelongTo;
        this.contents = contents;
        this.active = "active";
    }

    @Override
    public String toString() {
        return "comments [userid=" + userId + ", outTime=" + outTime
                + ", messagebelongTo=" + messagebelongTo + ", contents=" + contents
                + "]";
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public int getMessagebelongTo() {
        return messagebelongTo;
    }

    public void setMessagebelongTo(int messagebelongTo) {
        this.messagebelongTo = messagebelongTo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }


}
