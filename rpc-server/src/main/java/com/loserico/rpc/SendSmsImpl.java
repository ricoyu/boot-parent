package com.loserico.rpc;

import com.loserico.rpc.vo.UserInfo;

/**
 * <p>
 * Copyright: (C), 2023-10-30 17:39
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class SendSmsImpl implements SendSms{
	@Override
	public void sendMail(UserInfo userInfo) {
		System.out.println("客户端调用了服务端的sendMail方法");
		System.out.println("Send mail to " + userInfo.getName() + " with phone " + userInfo.getPhone());
		
	}
}
