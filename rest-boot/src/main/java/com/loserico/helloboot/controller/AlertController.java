package com.loserico.helloboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AlertController {
	
	@PostMapping("/alert")
	public String alert() {
		log.warn("收到告警了...");
		return "ok";
	}
}
