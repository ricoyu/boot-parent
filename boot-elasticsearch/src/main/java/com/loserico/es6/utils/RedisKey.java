package com.loserico.es6.utils;

import java.text.MessageFormat;

/**
 * <p>
 * Copyright: (C), 2020/7/3 10:53
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class RedisKey {
	
	private static final String KEY_USER = "searcg-mgr_user_%d"; //用户信息
	
	private static final String CACHE_KEY = "search_mgr_%s:%s";
	
	public static String cacheKeys(String biz, String key) {
		return MessageFormat.format(CACHE_KEY, biz, key);
	}
	
	public static String keyUser(int userId) {
		return MessageFormat.format(KEY_USER, userId);
	}
}
