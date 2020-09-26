package com.loserico.bootmybatisplus.service.impl;

import com.loserico.bootmybatisplus.entity.User;
import com.loserico.bootmybatisplus.mapper.UserMapper;
import com.loserico.bootmybatisplus.service.UserService;
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
	public User getUserById(Long userId) {
		return userMapper.queryById(userId);
	}
}
