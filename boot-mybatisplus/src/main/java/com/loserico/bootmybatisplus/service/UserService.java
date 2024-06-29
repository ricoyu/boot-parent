package com.loserico.bootmybatisplus.service;


import com.loserico.bootmybatisplus.entity.User;

/**
 * <p>
 * Copyright: (C), 2020/4/17 14:19
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface UserService {
	
	public User getUserById(Long userId);
	
	public void createUser(User user);
}
