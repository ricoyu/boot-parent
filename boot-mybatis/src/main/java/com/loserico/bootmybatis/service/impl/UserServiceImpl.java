package com.loserico.bootmybatis.service.impl;

import com.loserico.bootmybatis.entity.User;
import com.loserico.bootmybatis.mapper.UserMapper;
import com.loserico.bootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public void createUser(User user) {
		userMapper.createUser(user);
	}
	
	@Override
	public User getUserById(Long userId) {
		return userMapper.queryById(userId);
	}
}
