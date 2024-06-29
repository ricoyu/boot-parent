package com.loserico.boot.kafka.producer;

import com.loserico.boot.kafka.beans.Message;
import com.loserico.json.jackson.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * Copyright: (C), 2020-7-22 0022 18:40
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Component
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void send() {
		Message message = new Message();
		message.setId(System.currentTimeMillis());
		message.setMessage(UUID.randomUUID().toString());
		message.setSendTime(LocalDateTime.now());
		
		String json = JacksonUtils.toJson(message);
		log.info(">>>>>>>>>>>>>> message: {}", message);
		//topic在kafka中不需要提前创建
		kafkaTemplate.send("topic-007", json);
	}
}
