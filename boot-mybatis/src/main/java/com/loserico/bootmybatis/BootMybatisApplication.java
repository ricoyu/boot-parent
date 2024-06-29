package com.loserico.bootmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot整合Mybatis 
 * <p>
 * Copyright: Copyright (c) 2020-04-12 16:25
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
public class BootMybatisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootMybatisApplication.class, args);
	}
	
}
