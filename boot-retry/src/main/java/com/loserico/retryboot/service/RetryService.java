package com.loserico.retryboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RetryService {

	private AtomicInteger counter = new AtomicInteger(1);

	/**
	 * 重试机制发送短信
	 */
	@Retryable(
			retryFor = {IllegalArgumentException.class, ArrayIndexOutOfBoundsException.class},
			noRetryFor = {NullPointerException.class},
			maxAttempts = 4,
			backoff = @Backoff(delay = 2000L, multiplier = 2)
	)
	public boolean sendSmsRetry(int num) {
		log.info("第" + counter.getAndIncrement() + "次调用 当前时间：{}", getNowTime());
		// 使用随机数模拟重试场景
		return switch (num) {
			case 0 -> throw new IllegalArgumentException("参数有误！");
			case 1 -> throw new ArrayIndexOutOfBoundsException("数组越界！");
			case 2 -> true;
			case 3 -> throw new NullPointerException();
			default -> false;
		};
	}

	/**
	 * 兜底方法，规则：
	 * 1、超出了最大重试次数；
	 * 2、抛出了不进行重试的异常；
	 */
	@Recover
	public boolean recover() {
		log.info("兜底方案, 请稍后重试！{}", getNowTime());
		counter.set(1);
		return false;
	}

	/**
	 * 获取当前时间
	 */
	private String getNowTime() {

		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}