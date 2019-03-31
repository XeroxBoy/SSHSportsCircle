package com.cdut.sx.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component("comments")
@Table(name = "comments")
public class Comments {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue
    private int commentId;//评论的ID 主键
    @Column(name = "user_id")
    private String userId;//发布人
    @Column(name = "out_time")
    private String outTime;//发布时间
    @JoinColumn(name = "messagebelong_to")
    @ManyToOne(cascade = CascadeType.ALL)
    private Message messagebelongTo;//所属状态,外键
    @Column(name="contents")
    private String contents;//内容
    @Column(name="active")
    private String active;//是否可见

    public Comments() {
    }

    public Comments(int commentId, String userId, String outTime,
                    Message messagebelongTo, String contents) {
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

    public Message getMessagebelongTo() {
        return messagebelongTo;
    }

    public void setMessagebelongTo(Message messagebelongTo) {
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
