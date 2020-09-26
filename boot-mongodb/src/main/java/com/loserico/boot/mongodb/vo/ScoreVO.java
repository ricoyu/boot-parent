package com.loserico.boot.mongodb.vo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>
 * Copyright: (C), 2020-09-16 18:00
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@Document("scores")
public class ScoreVO {
	
	@Field("_id")
	private String id;
	
	private String studentId;
	
	private String course;
	
	private String score;
	
	private Integer count;
}
