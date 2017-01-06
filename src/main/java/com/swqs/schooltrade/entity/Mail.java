package com.swqs.schooltrade.entity;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private String account = "zhan_hello@163.com";
	private String password = "wbpufnzquzacnsdf";

	public void setSenderInfo(String account,String password){
		this.account=account;
		this.password=password;
	}
	public void sendMail(String receiver, String title, String content) throws Exception {
		// 环境变量设置。发送邮件时才需要
		Properties props = new Properties();
		// 发送使用的协议
		props.setProperty("mail.transport.protocol", "smtp");
		// 发送服务器的主机地址
		props.setProperty("mail.host", "smtp.163.com");
		// 请求身份验证
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		// 代表一封邮件
		MimeMessage message = new MimeMessage(session);
		// 设置发件人
		message.setFrom(new InternetAddress(account));
		// 设置收件人
		message.setRecipients(Message.RecipientType.TO, receiver);
		// 设置主题
		message.setSubject(title);

		// 设置邮件的正文内容
		message.setText(content);
		message.saveChanges();
		// 发送邮件
		Transport ts = session.getTransport();// 得到火箭
		ts.connect(account, password);// 连接
		ts.sendMessage(message, message.getAllRecipients());

		ts.close();
	}
	
	public static void main(String[] args) {
		Mail mail=new Mail();
		try {
			int code=new Random().nextInt(900000)+100000;
			mail.sendMail("619700505@qq.com", "校园跳蚤验证码", "您的验证码为:"+code);
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
