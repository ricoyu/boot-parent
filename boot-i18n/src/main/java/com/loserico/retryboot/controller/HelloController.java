package com.loserico.retryboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class HelloController {
	
	@CrossOrigin()
	@GetMapping("/hello")
	public String hello() {
		return "hi";
	}
}
