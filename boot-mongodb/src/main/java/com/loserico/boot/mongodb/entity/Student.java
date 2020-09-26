package com.loserico.boot.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * Copyright: (C), 2020-09-14 17:32
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@Document
public class Student {
	
	@Id
	private String id;
	
	private String name;
	
	private Integer age;
	
	private Grade grade;
	
	@Data
	public static class Grade {
		private Integer redis;
		private Integer zookeeper;
		private Integer dubbo;
	}
}
