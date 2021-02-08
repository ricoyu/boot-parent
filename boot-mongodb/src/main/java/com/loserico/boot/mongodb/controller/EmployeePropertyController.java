package com.loserico.boot.mongodb.controller;

import com.loserico.boot.mongodb.entity.Employee;
import com.loserico.boot.mongodb.service.EmployeePropertyService;
import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-24 14:06
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeePropertyController {
	
	@Autowired
	private EmployeePropertyService employeePropertyService;
	
	@GetMapping("/{property}/{value}")
	public Result findByProperty(@PathVariable String property, @PathVariable LocalDateTime value) {
		List<Employee> employees = employeePropertyService.findByProperty(Employee.class, property, value);
		return Results.success().result(employees);
	}
	
}
