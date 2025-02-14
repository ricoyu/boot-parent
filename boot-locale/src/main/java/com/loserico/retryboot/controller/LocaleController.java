package com.loserico.retryboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * <p>
 * Copyright: (C), 2020-09-02 16:26
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/locale")
public class LocaleController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/message")
	public String message(Locale locale) {
		return messageSource.getMessage("message", null, locale);
	}
}
