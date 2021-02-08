package com.loserico.boot.mongodb.service.impl;

import com.loserico.boot.mongodb.entity.SandboxMgr;
import com.loserico.boot.mongodb.service.MongoScriptService;
import com.loserico.mongo.dao.CriteriaOperations;
import com.loserico.mongo.dao.EntityOperations;
import com.loserico.mongo.dao.ScriptOperations;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Copyright: (C), 2020-10-19 11:49
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class MongoScriptServiceImpl implements MongoScriptService {
	
	@Autowired
	private EntityOperations entityOperations;
	
	@Autowired
	private CriteriaOperations criteriaOperations;
	
	@Autowired
	private ScriptOperations scriptOperations;
	
	@Override
	public Object scriptQuery(String script) {
		return null;
	}
	
	@Override
	public long update(String status) {
		UpdateResult updateResult = scriptOperations.updateOne("sandboxmgr", 
				"{'_id': '5f86ac49a9bb68140f50ef35'}", 
				"{'status' : '#{status}', 'ctime' : '2020-10-14 15:44:16'}", status);
		//UpdateResult updateResult = scriptOperations.updateOne("sandboxmgr", "{'_id': '5f86ac49a9bb68140f50ef35'}", "{'status' : '#{status}'}", status);
		//UpdateResult updateResult = scriptOperations.updateField("sandboxmgr", "{'_id': '5f86ac49a9bb68140f50ef35'}", "status", status);
		return updateResult.getModifiedCount();
	}
	
	@Override
	public SandboxMgr updateAndGet() {
		UpdateResult updateResult = scriptOperations.updateField("sandboxmgr", 
				"{'_id': '5f86ac49a9bb68140f50ef35'}", 
				"status", "Starting");
		System.out.println(updateResult.getModifiedCount());
		SandboxMgr entity = scriptOperations.findOne("{'_id': '5f86ac49a9bb68140f50ef35'}", SandboxMgr.class);
		return entity;
	}
}
