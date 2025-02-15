package com.loserico.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	@PostConstruct
	public void testSendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("yuxuehua@rzhrt.com.cn");
		message.setTo("ricoyu_521@hotmail.com");
		message.setSubject("这次来自三少爷的测试邮件");
		message.setText("哥, 你太帅了!");

		mailSender.send(message);
		System.out.println("发送成功");
	}
}
