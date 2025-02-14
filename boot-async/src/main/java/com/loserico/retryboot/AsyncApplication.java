package com.loserico.retryboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.springframework.context.annotation.AdviceMode.ASPECTJ;

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
@EnableAsync(mode = ASPECTJ, proxyTargetClass = true)
@SpringBootApplication
public class AsyncApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AsyncApplication.class, args);
	}
	
}
