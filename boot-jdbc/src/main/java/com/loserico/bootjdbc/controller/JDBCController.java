package com.loserico.bootjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020/4/12 12:20
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
public class JDBCController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/commodities")
	public List<Map<String, Object>> queryForCommodity() {
		List<Map<String, Object>> commodities = jdbcTemplate.queryForList("select * from cm_commodity_library order by id asc LIMIT 0, 10");
		return commodities;
	}
}
