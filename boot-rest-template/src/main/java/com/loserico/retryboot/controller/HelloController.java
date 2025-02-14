package com.loserico.retryboot.controller;

import com.loserico.common.lang.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	
	@Autowired
	private RestTemplate restTemplate;
	
	@CrossOrigin()
	@GetMapping("/hello")
	public String hello() {
		try {
			ReflectionUtils.getFieldValue("requestFactory", restTemplate);
			return restTemplate.getForEntity("http://192.168.100.11:8083/hello-boot/hello", String.class).getBody();
		}catch (Throwable e) {
			log.error("{}", e);
			return "read timeout";
		}
	}
}
