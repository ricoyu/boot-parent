package com.loserico.helloboot.controller;

import com.loserico.helloboot.vo.JavaCoffee;
import com.loserico.validation.utils.ValidationUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * Copyright: (C), 2021-02-02 16:32
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class ToughController {
	
	@PostMapping("/java-coffee")
	public boolean saveIp(@RequestBody @Valid JavaCoffee coffee) {
		return true;
	}
	
	@PostMapping("/manual-coffee")
	public boolean manualValidate(@RequestBody JavaCoffee coffee) {
		try {
			ValidationUtils.validate(coffee);
		} catch (BindException e) {
			return false;
		}
		return true;
	}
}
