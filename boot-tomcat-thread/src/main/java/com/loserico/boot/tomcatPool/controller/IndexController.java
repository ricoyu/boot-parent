package com.loserico.boot.tomcatPool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  
 * <p>
 * Copyright: Copyright (c) 2020-08-04 11:07
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@RestController
public class IndexController {

	@GetMapping("/tomcat/thread")
	public String tomcatThreadTest() throws InterruptedException {
		log.info("线程: {}", Thread.currentThread().getName());
		Thread.sleep(2000);
		return "Success";
	}
	
}