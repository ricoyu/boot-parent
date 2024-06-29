package com.loserico.bootproperties.controller;

import com.loserico.bootproperties.vo.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/")
@RestController
public class PersonController {
	
	@Autowired
	private Person person;
	
	@Value("${urls}")
	private List<String> urls;
	
	@GetMapping("/person")
	public Person person() {
		return person;	
	}
	
	@PostConstruct
	public void init() {
		for (String url : urls) {
			System.out.println(url);
		}
	}
}
