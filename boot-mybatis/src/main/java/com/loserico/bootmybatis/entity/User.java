package com.loserico.bootmybatis.entity;

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
public class User {
	
	private Long id;
	
	private String name;
	
	private boolean gendle;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
}
