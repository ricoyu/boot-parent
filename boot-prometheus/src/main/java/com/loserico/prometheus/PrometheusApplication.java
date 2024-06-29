package com.loserico.prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试SpringBoot整合Prometheus 
 * <p>
 * Copyright: Copyright (c) 2021-11-15 9:25
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@SpringBootApplication
public class PrometheusApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PrometheusApplication.class, args);
	}
	
}
