package com.loserico.bootredis.service;

import com.loserico.cache.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>
 * Copyright: (C), 2021-04-19 10:34
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class JedisService {
	
	@PostConstruct
	public void test() {
		log.info("JedisService.test() invoked");
		redisError();
	}
	
	public void redisError() {
		try {
			JedisUtils.set("k1", "v1");
		} catch (Exception e) {
			log.error("redis异常", e);
			redisError();
		}
	}
}
