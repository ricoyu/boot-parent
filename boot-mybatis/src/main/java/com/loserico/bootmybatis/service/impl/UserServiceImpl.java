package com.loserico.bootmybatis.service.impl;

import com.loserico.bootmybatis.entity.User;
import com.loserico.bootmybatis.mapper.UserMapper;
import com.loserico.bootmybatis.service.UserService;
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
	@Override
	public void createUser(User user) {
		user.setCreateTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		Concurrent.execute(() -> {
			user.setName("ricoyu");
			userMapper.createUser(user);
		});
		userMapper.createUser(user);
		Concurrent.await();
	}
	
	@Override
	public User getUserById(Long userId) {
		return userMapper.queryById(userId);
	}
}
