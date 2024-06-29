package com.loserico.rpc.base;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 注册服务
 * <p>
 * Copyright: (C), 2023-10-30 16:58
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class RegisterService {
	
	/**
	 * 本地可提供服务的容器
	 */
	private static final ConcurrentMap<String, Class> serviceCache = new ConcurrentHashMap<>();
	
	public void regService(String serviceName, Class serviceClass) {
		serviceCache.put(serviceName, serviceClass);
	}
	
	public Class getLocalService(String name) {
		return serviceCache.get(name);
	}
}
