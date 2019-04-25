package com.cdut.sx.utils;


import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtils {

    public static MimeMessage createSimpleMail(javax.mail.Session session, int n)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("sb827312773@163.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("1121062986@qq.com"));
        //邮件的标题
        message.setSubject("运动圈今日第" + n + "次启动");
        //邮件的文本内容
        message.setContent("你学你🐎呢", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
}

