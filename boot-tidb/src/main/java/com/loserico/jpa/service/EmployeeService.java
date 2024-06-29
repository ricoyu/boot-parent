package com.loserico.jpa.service;

import com.loserico.common.lang.vo.Page;
import com.loserico.jpa.entity.Employee;
import com.loserico.jpa.vo.EmployeeQueryVO;
import com.loserico.orm.dao.SQLOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2023-09-30 21:33
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class EmployeeService {
	
	@Autowired
	private SQLOperations sqlOperations;
	
	public List<Employee> listEmployees(Page page) {
		return sqlOperations.query4Page("listEmployees", Employee.class, page);
	}
	
	public List<Employee> listEmployees(EmployeeQueryVO queryVO) {
		Map<String, Object> params = new HashMap<>();
		params.put("fullName", queryVO.getFullName());
		params.put("lowSalary", queryVO.getLowSalary());
		params.put("highSalary", queryVO.getHighSalary());
		return sqlOperations.query4Page("queryEmployees", params, Employee.class, queryVO.getPage());
	}
}
