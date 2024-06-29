package com.loserico.bootredis.controller;

import com.loserico.cache.JedisUtils;
import com.loserico.cache.concurrent.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * Copyright: (C), 2020-11-27 11:57
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@RestController
public class RedisBlocklockController {
	
	@GetMapping("/lock")
	public String lock(String name) {
		Lock lock = JedisUtils.blockingLock("pcap-replay");
		lock.lock();
		
		System.out.println("运行业务代码...");
		System.out.println("业务代码抛异常...");
		if (true) {
			throw new RuntimeException();
		}
		lock.unlock();
		
		return "OK";
	}
	
	
	@GetMapping("/sub")
	public String subThread(String name) {
		/*Timer timer = new Timer("Loser Cache key renewval watch dog");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				log.info("Watch dog refresh lock timeout to default {} seconds", 1);
			}
		}, 20L, 200);
		
		if (true) {
			throw new RuntimeException();
		}*/
		Thread t = new Thread(() -> {
			while (true) {
				log.info("Watch dog refresh lock timeout to default {} seconds", 1);
				LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
			}
		});
		t.setDaemon(true);
		t.start();
		if (true) {
			throw new RuntimeException();
		}
		return "OK";
	}
}
