package com.loserico.boot.tomcat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
@Controller
public class IndexController {

	@Value("${spring.application.name}")
	private String appName;

	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "home";
	}

	@GetMapping("/tomcat/thread")
	public String tomcatThreadTest() throws InterruptedException {
		log.info("线程: {}", Thread.currentThread().getName());
		Thread.sleep(2000);
		return "Success";
	}
	
}