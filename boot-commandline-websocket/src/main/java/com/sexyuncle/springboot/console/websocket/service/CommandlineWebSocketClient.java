package com.sexyuncle.springboot.console.websocket.service;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * WebSocket纯Java命令行客户端 
 * <p>
 * Copyright: Copyright (c) 2020-08-20 17:22
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class CommandlineWebSocketClient extends WebSocketClient {
	
	public CommandlineWebSocketClient(String url) throws URISyntaxException {
		super(new URI(url));
	}
	
	@Override
	public void onOpen(ServerHandshake shake) {
		log.info("Handshaking...");
		for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext(); ) {
			String key = it.next();
			System.out.println(key + ":" + shake.getFieldValue(key));
		}
	}
	
	@Override
	public void onMessage(String paramString) {
		System.out.println("<<< " + paramString);
	}
	
	@Override
	public void onClose(int paramInt, String paramString, boolean paramBoolean) {
		System.out.println("Closing...");
	}
	
	@Override
	public void onError(Exception e) {
		log.error("Something goes wrong..." + e);
		
	}
}