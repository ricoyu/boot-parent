package com.loserico.bootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loserico.bootmybatisplus.entity.User;

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
public interface UserMapper extends BaseMapper<User> {
	
	User queryById(Long id);
}
