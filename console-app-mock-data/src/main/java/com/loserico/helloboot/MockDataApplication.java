package com.loserico.helloboot;

import com.loserico.helloboot.service.OutboundMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockDataApplication implements CommandLineRunner {
	
	@Autowired
	private OutboundMockService mockService;
	
	public static void main(String[] args) {
		SpringApplication.run(MockDataApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if (args.length == 0) {
			args[0] = "10";
		}
		if (args.length != 1) {
			System.out.println("Usage: java -jar boot-jpa.jar <number of records>");
			return;
		}
		mockService.mockOutbound(Integer.parseInt(args[0]));
	}
}
