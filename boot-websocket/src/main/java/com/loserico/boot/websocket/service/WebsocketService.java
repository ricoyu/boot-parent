package com.loserico.boot.websocket.service;

import com.loserico.boot.annotation.RedisListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Copyright: (C), 2020-09-11 19:46
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class WebsocketService {
	
	@RedisListener(channels = "weekend")
	public void command(String chennel, String message) {
		log.info("Channel: {} Message: {}", chennel, message);
	}
	
	@RedisListener(channels = "weekend2")
	public void command2(String chennel, String message) {
		log.info("Channel: {} Message: {}", chennel, message);
	}
}
