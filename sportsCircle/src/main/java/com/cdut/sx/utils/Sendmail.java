package com.cdut.sx.utils;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Transport;
import java.util.Properties;

import static com.cdut.sx.utils.MailUtils.createSimpleMail;

public class Sendmail {
    public static int n = 1;

    public static void sendMail() {
        System.out.println("开始发送");
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        javax.mail.Session session = javax.mail.Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = null;
        try {
            ts = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        try {
            ts.connect("sb827312773@163.com", "daohaodegun87549");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //4、创建邮件
        javax.mail.Message message = null;
        try {
            message = createSimpleMail(session, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5、发送邮件
        try {
            ts.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            ts.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}