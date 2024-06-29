package com.loserico.schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

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
@Slf4j
@Service
public class ScheduledJob {
	
	private int i;
	
	private AtomicLong counter = new AtomicLong();
	
	//@Scheduled(fixedRate = 1000L)
	public void schedule() {
		System.out.println("...");
		i++;
		if ( i % 3 == 0) {
			throw new RuntimeException(""+i);
		}
	}
	
	@Scheduled(cron = "0/1 * * * * *")
	public void cronSchedule() {
		log.info("第{}次执行", counter.incrementAndGet());
	}
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
}
