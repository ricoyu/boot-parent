package com.loserico.bootmybatisplus.controller;

import com.loserico.bootmybatisplus.entity.User;
import com.loserico.bootmybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020/4/17 14:21
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}
}
