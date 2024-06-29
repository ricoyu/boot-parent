package com.loserico.rpc.config;

import com.loserico.rpc.SendSms;
import com.loserico.rpc.rpc.RpcClientFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Copyright: (C), 2023-10-30 18:33
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class BeanConfig {
	
	@Autowired
	private RpcClientFrame rpcClientFrame;
	
	@Bean
	public SendSms sendSms() {
		SendSms sendSms = rpcClientFrame.getRemoteProxyObj(SendSms.class);
		System.out.println("生成代理类成功!");
		return sendSms;
	}
}
