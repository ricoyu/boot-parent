package com.loserico.bootmybatis.mapper;

import com.loserico.bootmybatis.entity.User;

/**
 * <p>
 * Copyright: (C), 2020/4/17 14:15
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface UserMapper {
	
	User queryById(Long id);
	
	void createUser(User user);
}
