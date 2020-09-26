package com.loserico.boot.kafkastreaming;

import com.loserico.boot.kafkastreaming.beans.WordCountDemo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  
 * <p>
 * Copyright: Copyright (c) 2020-09-24 13:46
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@SpringBootApplication
public class BootKafkaStreamingApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(BootKafkaStreamingApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		WordCountDemo wordCountDemo = new WordCountDemo();
		wordCountDemo.start();
				
	}
}
