package com.loserico.bootproperties.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020/4/10 12:32
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "person")
public class Person {
	
	private String name;
	
	private Integer age;
	
	private double salary;
	
	private boolean marriage;
	
	private Car car;
	
	private List<String> hobbies;
	
	private Map<String,Object> maps;
	
	private LocalDate birthday;
}
