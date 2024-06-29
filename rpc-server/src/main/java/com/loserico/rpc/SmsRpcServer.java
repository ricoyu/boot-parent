package com.loserico.rpc;

import com.loserico.rpc.rpc.RpcServerFrame;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

/**
 * <p>
 * Copyright: (C), 2023-10-30 17:36
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class SmsRpcServer {
	
	@Autowired
	private RpcServerFrame rpcServerFrame;
	
	@PostConstruct
	public void server() throws IOException {
		Random random = new Random();
		int port = 8778;
		rpcServerFrame.startService(SendSms.class.getName(), "127.0.0.1", port, SendSmsImpl.class);
	}
}
