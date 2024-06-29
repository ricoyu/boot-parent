package com.loserico.rpclocal;

import com.loserico.rpc.SendSms;
import com.loserico.rpc.vo.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RpcClientApplicationTests {
	
	@Autowired
	private SendSms sendSms;
	
	@Test
	void rpcTest() {
		long start = System.currentTimeMillis();
		//发送短信
		UserInfo userInfo = new UserInfo("rico", "13888888888");
		System.out.println("Send mail: ");
		sendSms.sendMail(userInfo);
		System.out.println("共耗时: " + (System.currentTimeMillis() - start) + "ms");
		
	}
	
}
