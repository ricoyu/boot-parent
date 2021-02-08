package com.loserico.schedule.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Copyright: (C), 2021-01-25 13:38
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class ScheduledJob {
	
	private int i;
	
	@Scheduled(fixedRate = 1000L)
	public void schedule() {
		System.out.println("...");
		i++;
		if ( i % 3 == 0) {
			throw new RuntimeException(""+i);
		}
	}
}
