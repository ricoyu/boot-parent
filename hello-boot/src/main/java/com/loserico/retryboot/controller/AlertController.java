package com.loserico.retryboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2024-01-05 15:07
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@RestController("")
@RequestMapping("/alert")
public class AlertController {
	
	@GetMapping("/hello")
	public String alert() {
		return "要被裁员了";
	}
}
