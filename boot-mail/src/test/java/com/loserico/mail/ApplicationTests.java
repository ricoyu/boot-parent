package com.loserico.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void testSendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("yuxuehua@rzhrt.com.cn");
		message.setTo("ricoyu_521@hotmail.com");
		message.setSubject("这次来自三少爷的测试邮件");
		message.setText("哥, 你很帅!");
		
		mailSender.send(message);
	}

}