package com.loserico.javaee.controller;

import com.loserico.common.spring.annotation.SmartLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * Copyright: (C), 2020/4/10 20:29
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class JavaEEController {
	
	@SmartLogger
	@GetMapping("/service")
	public Object service(@RequestParam String name) {
		return name + LocalDateTime.now();
	}
}
