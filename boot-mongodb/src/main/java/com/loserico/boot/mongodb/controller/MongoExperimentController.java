package com.loserico.boot.mongodb.controller;

import com.loserico.boot.mongodb.entity.SandboxMgr;
import com.loserico.boot.mongodb.service.MongoScriptService;
import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020-10-19 11:46
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/mg")
public class MongoExperimentController {
	
	@Autowired
	private MongoScriptService mongoScriptService;
	
	@PostMapping("/update")
	public Result updateSingleColumn(String status) {
		long count = mongoScriptService.update(status);
		return Results.success().result(count);
	}
	
	@PostMapping("/updateGet")
	public Result updateGet(String status) {
		SandboxMgr entity = mongoScriptService.updateAndGet();
		return Results.success().result(entity);
	}
}
