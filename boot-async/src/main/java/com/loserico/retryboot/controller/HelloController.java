package com.loserico.retryboot.controller;

import com.loserico.retryboot.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

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
	private AsyncService asyncService;
	
	@CrossOrigin()
	@GetMapping("/hello")
	public String hello() throws ExecutionException, InterruptedException {
		asyncService.helloAsync();
		return asyncService.asyncReturn().get();
	}
}
