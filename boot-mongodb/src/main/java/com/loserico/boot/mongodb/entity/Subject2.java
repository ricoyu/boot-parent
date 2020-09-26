package com.loserico.boot.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-09-14 17:59
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@Document
public class Subject2 {
	
	private String id;
	
	private String name;
	
	private List<String> subjects;
	
	@Data
	public static class Subject3 {
		private String name;
		
		private Integer hour;
	}
}
