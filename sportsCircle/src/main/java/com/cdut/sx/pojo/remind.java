package com.cdut.sx.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="remind")
public class remind {


    private int remindId;//主键
    private String userFrom;//发送者
    private String userTo;//接受者
    private int messagebelongTo;//所属状态,外键
    private String active = "active";//是否向应约者显示此约定,dead,active 两种取值

    public int getRemindId() {
        return remindId;
    }

    public void setRemindId(int remindId) {
        this.remindId = remindId;
    }

    public int getMessagebelongTo() {
        return messagebelongTo;
    }

    public void setMessagebelongTo(int messagebelongTo) {
        this.messagebelongTo = messagebelongTo;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}

