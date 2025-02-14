package com.loserico.retryboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *  
 * <p>
 * Copyright: Copyright (c) 2020-09-27 16:32
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@EnableAsync
@SpringBootApplication
public class SpringDataRedisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisApplication.class, args);
	}
	
}
