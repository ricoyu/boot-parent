package com.loserico.rpc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Copyright: (C), 2023-10-30 16:44
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
public class UserInfo implements Serializable {
	
	private String name;
	
	private String phone;
	
	public UserInfo(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
}
