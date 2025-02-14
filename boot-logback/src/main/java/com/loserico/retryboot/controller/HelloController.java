package com.loserico.retryboot.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

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
public class HelloController {
	
	private AtomicLong counter = new AtomicLong(1);
	
	@CrossOrigin()
	@GetMapping("/hello")
	public String hello() {
		return "hi";
	}
	
	@PostConstruct
	public void genLog() {
		for (int i = 0; i < 1000000; i++) {
			log.info("生成日志, 测试都是会未达大小限制的日志文件"+counter.getAndIncrement());
		}
	}
}
