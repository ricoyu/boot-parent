package com.loserico.boot.mongodb.service;

import com.loserico.boot.mongodb.vo.ScoreVO;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-09-16 17:59
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface ScoreService {
	
	public List<ScoreVO> scoreStatistic();
}
