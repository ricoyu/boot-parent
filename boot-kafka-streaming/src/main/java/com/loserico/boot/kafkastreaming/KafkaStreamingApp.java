package com.loserico.boot.kafkastreaming;

import com.loserico.boot.kafkastreaming.beans.KafkaStreamsYellingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Copyright: (C), 2020-09-29 11:46
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@SpringBootApplication
public class KafkaStreamingApp implements CommandLineRunner {
	
	@Autowired
	private KafkaStreamsYellingApp kafkaStreamsYellingApp;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamingApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		kafkaStreamsYellingApp.start();
	}
}
