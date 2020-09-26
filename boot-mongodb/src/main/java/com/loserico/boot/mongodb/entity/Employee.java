package com.loserico.boot.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * Copyright: (C), 2020-7-29 0029 13:37
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@Document("emp")
public class Employee {
	
	@Id
	private Integer id;
	
	private String name;
	
	private String job;
	
	private String dep;
	
	private Integer salary;
}
