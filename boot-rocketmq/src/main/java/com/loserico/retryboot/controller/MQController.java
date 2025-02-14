package com.loserico.retryboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020-11-10 16:12
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("")
public class MQController {
	
	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	
	/**
	 * 测试发送消息
	 * @return
	 */
	@PostMapping("/send")
	public String send(String message) {
		rocketMQTemplate.convertAndSend("test-topic-1", message);
		return "success";
	}
	
	@CrossOrigin()
	@GetMapping("/hello")
	public String hello() {
		return "hi";
	}
}
