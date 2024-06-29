package com.loserico.bootproperties;

import com.loserico.bootproperties.vo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({Person.class})
public class BootPropertiesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootPropertiesApplication.class, args);
	}
	
}
