package com.cdut.sx.utils;/*


package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



*/
/**
 * 邮件发送的工具类
 *
 * @author LeeB
 * <p>
 * <p>
 * 发送邮件的方法
 * @param to  给谁发邮件
 * @param code 邮件的激活码
 * @throws MessagingException
 * @throws Exception
 * <p>
 * 发送邮件的方法
 * @param to  给谁发邮件
 * @param code 邮件的激活码
 * @throws MessagingException
 * @throws Exception
 * <p>
 * 发送邮件的方法
 * @param to  给谁发邮件
 * @param code 邮件的激活码
 * @throws MessagingException
 * @throws Exception
 *//*

public class MailUtils {
		*/
/**
 * 发送邮件的方法
 * @param to  给谁发邮件
 * @param code 邮件的激活码
 * @throws MessagingException
 * @throws Exception
 *//*

		public static void sendMail(String to ,String code) throws Exception{
			// 1.创建连接对象，链接到邮件服务器
			Properties props =new Properties();
			//props.setProperty("host",value);
			Session session =Session.getInstance(props, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("service@store.com","111");//连接到eyouMailServer
			}
				
			});
			//2.创建邮件对象
			Message message = new MimeMessage(session);
			
			//2.1设置发件人
			message.setFrom(new InternetAddress("service@store.com"));
			//2.2设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//2.3设置邮件主题
			message.setSubject("激活邮件");
			//2.4设置邮件正文
			message.setContent("<h1>来自xx网站的激活邮件，激活请点击一下链接：</h1><h3><a href='http://localhost:8080/sportsCircle/ActiveServlet?code="+code+"'>http://localhost:8080/sportsCircle/ActiveServlet?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//3.发送一封激活邮件
			Transport.send(message);
			
		}
}
*/
