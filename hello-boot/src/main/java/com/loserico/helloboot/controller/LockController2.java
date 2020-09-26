package com.loserico.helloboot.controller;

import com.loserico.cache.JedisUtils;
import com.loserico.cache.concurrent.Lock;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020/4/13 12:35
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@Slf4j
public class LockController2 {
	
	@SneakyThrows
	@GetMapping("/lock2")
	public String lock() {
		Lock lock = JedisUtils.blockingLock("lock");
		lock.lock();
		log.info("LockController2 线程[{}] 成功加锁!", Thread.currentThread().getName());
		lock.unlock();
		log.info("LockController2 线程[{}] 释放锁!", Thread.currentThread().getName());
		return "OK";
	}
}
