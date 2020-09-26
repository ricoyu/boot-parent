package com.loserico.boot.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020-7-26 0026 19:17
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class KafkaController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@PostMapping("/send")
	public void send() {
		for (int i = 0; i < 30; i++) {
			kafkaTemplate.send("test", "This is a message from KafkaTemplate " + i);
			//kafkaTemplate.send("test", 0, "key", "this is a msg");
		}
	}
}
