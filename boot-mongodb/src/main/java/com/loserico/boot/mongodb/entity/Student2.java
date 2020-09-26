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
@Document("student")
public class Student2 {
	
	@Id
	private String id;
	
	private String name;
	
	private String result;
	
	private Marks marks;
	
	@Data
	static class Marks{
		private String english;
		private String maths;
	}
}
