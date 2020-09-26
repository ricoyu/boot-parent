package com.loserico.boot.https;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * https://www.baeldung.com/spring-boot-security-autoconfiguration
 * 
 * SpringBoot HTTPS 演示 
 * 
 * <p>
 * Copyright: Copyright (c) 2020-08-03 10:23
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class BootHttpsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootHttpsApplication.class, args);
	}
	
}
