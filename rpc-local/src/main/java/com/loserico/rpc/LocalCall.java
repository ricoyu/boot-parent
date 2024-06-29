package com.loserico.rpc;

import com.loserico.rpc.vo.UserInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Copyright: (C), 2023-10-30 17:17
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class LocalCall {
	
	private SendSms sendsms;
	
	public void processOrder() {
		long start = System.currentTimeMillis();
		UserInfo userInfo = new UserInfo("rico", "13888888888");
		System.out.println("Send mail: ");
		sendsms.sendMail(userInfo);
		long end = System.currentTimeMillis();
		System.out.println("Send mail cost: " + (end - start) + "ms");
	}
}
