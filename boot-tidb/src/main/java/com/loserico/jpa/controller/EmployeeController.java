package com.loserico.jpa.controller;

import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import com.loserico.jpa.entity.Employee;
import com.loserico.jpa.service.EmployeeService;
import com.loserico.jpa.vo.EmployeeQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Copyright: (C), 2023-09-30 21:30
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	/**
	 * 分页查询员工表
	 */
	@PostMapping("/list")
	public Result listEmployees(@RequestBody EmployeeQueryVO queryVO) {
		List<Employee> employees = employeeService.listEmployees(queryVO);
		List<String> names = employees.stream().map(Employee::getFullName).collect(toList());
		return Results.success().result(names);
	}
}
