package com.loserico.boot.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>
 * Copyright: (C), 2020-10-19 15:54
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
public class IpDto {
	
	/**
	 * IP地址
	 */
	@Field("IP")
	private String ip;
	
	/**
	 * IP地址版本
	 */
	@Field("Version")
	private String version;
	
	/**
	 * 子网掩码
	 */
	@Field("Mask")
	private String mask;
}
