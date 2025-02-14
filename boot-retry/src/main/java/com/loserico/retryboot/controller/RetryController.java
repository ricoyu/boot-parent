package com.loserico.retryboot.controller;

import com.loserico.retryboot.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/retry")
public class RetryController {

	@Autowired
	private RetryService retryService;
	
	@GetMapping(value = "/send-sms/{num}")
	public void hello(@PathVariable int num) throws InterruptedException {
		retryService.sendSmsRetry(num);
	}
}
