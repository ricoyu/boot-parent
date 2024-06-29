package com.loserico.bootmybatisplus.service.impl;

import com.loserico.bootmybatisplus.entity.User;
import com.loserico.bootmybatisplus.mapper.UserMapper;
import com.loserico.bootmybatisplus.service.UserService;
import com.loserico.common.lang.concurrent.Concurrent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * Copyright: (C), 2020/4/17 14:20
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public void createUser(User user) {
		user.setCreateTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		Concurrent.execute(() -> {
			user.setName("ricoyu");
			userMapper.insert(user);
		});
		userMapper.insert(user);
		Concurrent.await();
		if (true) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public User getUserById(Long userId) {
		return userMapper.queryById(userId);
	}
}
