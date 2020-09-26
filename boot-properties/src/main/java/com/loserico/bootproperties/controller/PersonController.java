package com.loserico.bootproperties.controller;

import com.loserico.bootproperties.vo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020/4/10 12:36
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class PersonController {
	
	@Autowired
	private Person person;
	
	@GetMapping("/person")
	public Person person() {
		return person;	
	}
}
