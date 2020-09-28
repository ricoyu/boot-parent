package com.loserico.helloboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Copyright: (C), 2020-09-27 16:32
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class HeaderController {
	
	@GetMapping("/header/{header}")
	public String header(@PathVariable String header, HttpServletRequest request) {
		return request.getHeader(header);
	}
}
