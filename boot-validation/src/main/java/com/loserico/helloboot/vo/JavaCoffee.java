package com.loserico.helloboot.vo;

import com.loserico.validation.validation.annotation.IP;
import lombok.Data;

/**
 * <p>
 * Copyright: (C), 2021-02-02 16:33
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
public class JavaCoffee {
	
	@IP(message = "IP 地址不合法")
	private String ip;
}
