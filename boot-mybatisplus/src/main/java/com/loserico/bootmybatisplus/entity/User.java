package com.loserico.bootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * Copyright: (C), 2020/4/17 14:13
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@TableName("user")
public class User {
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	private String name;
	
	private boolean gendle;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
}
