package com.loserico.boot.mongodb;

import com.loserico.boot.mongodb.support.SoftDeleteMongoRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * <p>
 * Copyright: (C), 2020-7-29 0029 13:45
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@SpringBootApplication
@EnableMongoRepositories(repositoryFactoryBeanClass = SoftDeleteMongoRepositoryFactoryBean.class)
public class MongoWebApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MongoWebApplication.class, args);
	}
}
