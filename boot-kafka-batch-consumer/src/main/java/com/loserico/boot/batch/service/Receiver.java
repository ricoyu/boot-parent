package com.loserico.boot.batch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-19 11:00
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class Receiver {
	
	@KafkaListener(topics = "${app.topic.batch}")
	public void receive(@Payload List<String> messages,
	                    @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
	                    @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
		log.info("Starting to consume batch messages");
		// Sender that have sent a total of 9 messages, then it will be consumed by three batches
		for (int i = 0; i < messages.size(); i++) {
			log.info("Received message='{}' with partition-offset='{}'", messages.get(i), partitions.get(i) + "-" + offsets.get(i));
		}
		log.info("All messages consumed");
	}
}
