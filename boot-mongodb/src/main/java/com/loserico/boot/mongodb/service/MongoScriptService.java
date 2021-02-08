package com.loserico.boot.mongodb.service;

import com.loserico.boot.mongodb.entity.SandboxMgr;

/**
 * <p>
 * Copyright: (C), 2020-08-24 15:12
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface MongoScriptService {
	
	public Object scriptQuery(String script);
	
	public long update(String status);
	
	public SandboxMgr updateAndGet();
}
