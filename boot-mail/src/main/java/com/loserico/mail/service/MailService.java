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
		message.setSubject("OA审批流催办邮件");
		message.setText("浦佳栋提醒您审批加班申请");

		mailSender.send(message);
		System.out.println("发送成功");
	}
}
