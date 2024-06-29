package com.loserico.bootmybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BootMybatisplusApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootMybatisplusApplication.class, args);
	}
	
}
