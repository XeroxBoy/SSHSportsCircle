package com.cdut.sx.utils;/*
package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;

public class Sendmail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			pushMessage("827312773@qq.com","哦哦");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	  public static void pushMessage(String userEmail,String content) throws AddressException, MessagingException {  
          Properties prop = new Properties();  
         
         String smtpHostName = "smtp." + userEmail.split("@")[1];//用split方法截取 XX.COM
        if(smtpHostName.indexOf("qq")!=-1){//qq要特殊处理
        	//smtpHostName="smtp.exmail.qq.com";
        	  MailSSLSocketFactory sf = null;
			try {
				sf = new MailSSLSocketFactory();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	    sf.setTrustAllHosts(true);
        //	 prop.setProperty("mail.smtp.socketFactory.class", "javax.NET.ssl.SSLSocketFactory"); 
        	 prop.setProperty("mail.smtp.ssl.enable", "true");
        	    prop.put("mail.smtp.ssl.socketFactory", sf);
        	 prop.setProperty("mail.smtp.port", "465"); 
        	 prop.setProperty("mail.smtp.socketFactory.port", "465"); 
        	 
        
        }
 
          prop.setProperty("mail.transport.protocol", "smtp");//定义邮件发送协议  
          prop.setProperty("mail.smtp.host", smtpHostName);//声明邮件服务器地址  
          prop.setProperty("mail.smtp.auth", "true");//发送权限，为true时表示允许发送  
*/
/*          prop.setProperty("mail.debug", "true");//设置为true时，调试的时候可以在控制台显示信息
 *//*

            
          Session session = Session.getInstance(prop);//相当于建立了一条通信路线  
          session.setDebug(true);
          Message msg = new MimeMessage(session);  
          msg.setFrom(new InternetAddress("1024300613@qq.com"));//发件者邮箱  
          msg.setRecipient(RecipientType.TO, new InternetAddress(userEmail));//收件邮箱  
          msg.setSubject("这是1024300613@qq.com发送给"+userEmail+"的邮件XXXXXXXXXX");  
          msg.setText(content);  
            
          Transport tran = session.getTransport();  
          tran.connect("1024300613@qq.com", "sdiisnygeopsbaia");//Q号，密码
          tran.sendMessage(msg,msg.getAllRecipients());  
            
        
        
  }  
}
*/
