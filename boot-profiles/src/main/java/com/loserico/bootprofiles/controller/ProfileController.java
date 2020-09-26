package com.loserico.bootprofiles.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020/4/10 11:13
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class ProfileController {
	
	@Value("${hello.message}")
	private String message;
	
	@GetMapping("/message")
	public String message() {
		return message;
	}
}
