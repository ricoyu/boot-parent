package com.loserico.boot.kafka.beans;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * Copyright: (C), 2020-7-22 0022 18:39
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
public class Message {
	
	private Long id;
	
	private String message;
	
	private LocalDateTime sendTime;
}
