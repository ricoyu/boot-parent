package com.loserico.helloboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

import static java.util.concurrent.TimeUnit.*;

/**
 * <p>
 * Copyright: (C), 2023-12-04 13:49
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class AsyncService {
	
	/**
	 * 无返回值的Async
	 */
	@Async
	public void helloAsync() {
		System.out.println("[" + Thread.currentThread().getName() + "] hello async ");
		try {
			SECONDS.sleep(2);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("[" + Thread.currentThread().getName() + "] hello async done ");
	}
	
	public Future<String> asyncReturn() {
		System.out.println("[" + Thread.currentThread().getName() + "] hello async return");
		try {
			SECONDS.sleep(2);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return new AsyncResult("hello async");
	}
}
