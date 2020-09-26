package com.loserico.boot.mongodb.controller;

import com.loserico.boot.mongodb.entity.Employee;
import com.loserico.boot.mongodb.service.EmployeeService;
import com.loserico.common.lang.vo.Page;
import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-7-29 0029 13:43
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeEntityController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/query")
	public List<Employee> queryEmployee() {
		return employeeService.queryEmployee();
	}
	
	@PostMapping("/paging")
	public Result pagingEmployee(@RequestBody Page page) {
		List<Employee> employees = employeeService.queryEmployee(null, page);
		return Results.success().page(page).results(employees);
	}
	
	@PostMapping("/table-paging")
	public Result pagingTableEmployee(Employee queryvo, Page page) {
		List<Employee> employees = employeeService.queryEmployee(queryvo, page);
		return Results.success().page(page).results(employees);
	}
	
	@GetMapping("/list")
	public Result findAll() {
		List<Employee> employees = employeeService.findAll();
		return Results.success().results(employees);
	}
	
	@PostMapping("/page")
	public Result findAll(Page page) {
		List<Employee> employees = employeeService.findAll(page);
		return Results.success()
				.page(page)
				.results(employees);
	}
	
	@PostMapping("")
	public Result saveEmployee(@RequestBody Employee employee) {
		employeeService.createEmployee(employee);
		return Results.success().result(employee);
	}
	
	@PostMapping("/batch/save")
	public Result batchSave(@RequestBody List<Employee> employees) {
		employeeService.saveAll(employees);
		return Results.success().results(employees);
	}
	
	@GetMapping("/{id}")
	public Result getEmployee(@PathVariable String id) {
		Employee employee = employeeService.findById(id);
		return Results.success().result(employee);
	}
	
	@GetMapping("/batch/{ids}")
	public Result getEmployees(@PathVariable String ids) {
		List<Employee> employees = employeeService.findByIds(ids.split("\\s*,\\s*"));
		return Results.success().results(employees);
	}
	
	@PostMapping("/{id}")
	public Result deleteEmployee(@PathVariable String id) {
		employeeService.deleteByPk(id);
		return Results.success().build();
	}
	
	@PostMapping("/batch/{ids}")
	public Result deleteEmployees(@PathVariable String ids) {
		employeeService.deleteByPks(ids.split("\\s*,\\s*"));
		return Results.success().build();
	}
	
	@PostMapping("/delete/all")
	public Result deleteAll() {
		int i = employeeService.deleteAll();
		return Results.success().result(i);
	}
	
	@GetMapping("/ensure/{id}")
	public Result encureEntityExists(@PathVariable String id) {
		Employee employee = employeeService.ensureEntityExists(id);
		return Results.success().result(employee);
	}
}
