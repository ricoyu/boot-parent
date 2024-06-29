package com.sexyuncle.springboot.console.websocket;

import com.loserico.common.lang.utils.IOUtils;
import com.sexyuncle.springboot.console.websocket.service.CommandlineWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Copyright: Copyright (c) 2020-08-20 17:24
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 *
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@SpringBootApplication
public class SpringConsoleApplication implements CommandLineRunner {
	
	private static final String ARG_NAME = "wsurl";
	
	public static void main(String[] args) {
		SpringApplication.run(SpringConsoleApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String uri = "ws://127.0.0.1:8081/ws/agent";
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				String arg = args[i];
				log.info("参数{}: {}", i, arg);
				if (arg.indexOf(ARG_NAME) == 0) {
					uri = arg.split("\\s*=\\s*")[1];
					break;
				}
			}
		}
		log.info("url: {}", uri);
		CommandlineWebSocketClient client = new CommandlineWebSocketClient(uri);
		client.connect();
		while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
			log.info("Connecting...");
			TimeUnit.SECONDS.sleep(1);
		}
		log.info("Connected.");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				client.sendPing();
				log.info("Send heartbeat...");
			}
		};
		new Timer().schedule(task, 60000, 60000);
		
		IOUtils.readCommandLine(message -> client.send(message));
	}
	
	
}
