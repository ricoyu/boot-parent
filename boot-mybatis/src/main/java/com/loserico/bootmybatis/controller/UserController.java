package com.loserico.bootmybatis.controller;

import com.loserico.bootmybatis.entity.User;
import com.loserico.bootmybatis.service.UserService;
import com.loserico.cache.JedisUtils;
import com.loserico.cache.concurrent.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public Long createUser(User user) {
		userService.createUser(user);
		return user.getId();
	}
	
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable Long userId) {
		Lock lock = JedisUtils.blockingLock("pcap-replay");
		try {
			lock.lock();
			return userService.getUserById(userId);
		} finally {
			if (lock.locked()) {
				lock.unlock();
			}
		}
	}
}
