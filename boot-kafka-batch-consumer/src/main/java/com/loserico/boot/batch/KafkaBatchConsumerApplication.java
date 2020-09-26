package com.loserico.boot.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Kafka 批量消费 
 * <p>
 * Copyright: Copyright (c) 2020-08-19 10:42
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@SpringBootApplication
public class KafkaBatchConsumerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaBatchConsumerApplication.class, args);
	}
	
}
