package com.cdut.sx.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Component("user")
public class User {
    @Id
    @GeneratedValue
    @Column(name="userId")
    private int userId;//主键
    @Column(name="birthday")
    private Date birthday;//生日
    @Column(name="userName")
    @NotBlank(message="{user.name.valid}")
    private String username; //主键
    @Column(name="userPassword")
    @NotBlank(message="{user.password.valid}")
    private String password;
    @Column(name="userEmail")
    private String email;
    @Column(name="sex")
    private String sex;
    @Column(name="maxProdays")
    private int maxProdays = 0;
    @Column(name="exp")
    private int exp = 0;//经验
    @Column(name="Prodays")
    private int Prodays = 0;//打卡天数
    @Column(name="lastProday")
    private Date lastProday = new Date();//上一次打卡的日期
    @Column(name="Areabelongto")
    private String Areabelongto;//所属板块
    @OneToMany
    private Set<Message> messages = new HashSet<Message>();//一用户有多条状态

    public User() {
        super();
    }


    public User(String birthday, String username, String password, String email,
                String sex, String areabelongto) {  //无打卡天数
        super();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            this.birthday = (Date) sdf.parse(birthday);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.Areabelongto = areabelongto;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(Date birthday, String username, String password, String email,
                String sex, String areabelongto) {
        super();
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.Areabelongto = areabelongto;
    }

    /*	private String code;//激活码
        private int state=0;//保存激活状态 默认为0 未激活
    */
    @Override
    public String toString() {
        return "user [userId=" + userId + ", birthday=" + birthday
                + ", username=" + username + ", password=" + password
                + ", email=" + email + ", sex=" + sex + ", maxProdays="
                + maxProdays + ", exp=" + exp + ", Prodays=" + Prodays
                + ", lastProday=" + lastProday + ", Areabelongto="
                + Areabelongto + ", messages=" + messages + "]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getProdays() {
        return Prodays;
    }

    public void setProdays(int prodays) {
        Prodays = prodays;
    }

    public String getAreabelongto() {
        return Areabelongto;
    }

    public void setAreabelongto(String areabelongto) {
        Areabelongto = areabelongto;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLastProday() {
        return lastProday;
    }

    public void setLastProday(Date lastProday) {
        this.lastProday = lastProday;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMaxProdays() {
        return maxProdays;
    }

    public void setMaxProdays(int maxProdays) {
        this.maxProdays = maxProdays;
    }


}