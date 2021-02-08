package com.loserico.boot.mongodb.controller;

import com.loserico.boot.mongodb.service.ScoreService;
import com.loserico.boot.mongodb.vo.ScoreVO;
import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-09-16 18:03
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/score")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	@GetMapping("/statistic")
	public Result statistic() {
		List<ScoreVO> scores = scoreService.scoreStatistic();
		return Results.success().result(scores);
	}
}
