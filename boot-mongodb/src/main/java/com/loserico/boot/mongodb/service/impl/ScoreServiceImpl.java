package com.loserico.boot.mongodb.service.impl;

import com.loserico.boot.mongodb.service.ScoreService;
import com.loserico.boot.mongodb.vo.ScoreVO;
import com.loserico.mongo.dao.ScriptOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020-09-16 18:02
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private ScriptOperations scriptOperations;
	
	@Override
	public List<ScoreVO> scoreStatistic() {
		Map<String, Object> params = new HashMap<>();
		params.put("score", 80);
		params.put("studentId", 1);
		return scriptOperations.aggregationQuery(ScoreVO.class, 80, "scoreStatistic");
	}
}
