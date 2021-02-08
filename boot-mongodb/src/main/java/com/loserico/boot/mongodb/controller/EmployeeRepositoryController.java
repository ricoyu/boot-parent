package com.loserico.boot.mongodb.controller;

import com.loserico.boot.mongodb.entity.Employee;
import com.loserico.boot.mongodb.repository.EmployeeRepository;
import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-24 16:49
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/employee/repository")
public class EmployeeRepositoryController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/all")
	public Result findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return Results.success().result(employees);
	}
	
	@GetMapping("/name/{name}")
	public Result findByName(@PathVariable String name) {
		List<Employee> employees = employeeRepository.findEmployeeByName(name);
		return Results.success().result(employees);
	}
}
