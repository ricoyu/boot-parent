package com.loserico.retryboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

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
@Component
@EnableScheduling
public class HelloService {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@CrossOrigin()
	@GetMapping("/hello")
	public String hello() {
		return "hi";
	}
	
	@Scheduled(fixedDelay = 100)
	public void testBrpop() {
		redisTemplate.opsForValue().set("k1", "ricoyu");
	}
	
}
