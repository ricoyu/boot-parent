package com.loserico.jpa.controller;

import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import com.loserico.jpa.entity.Department;
import com.loserico.jpa.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	private JpaService jpaService;

	@GetMapping("/list")
	public Result depts() {
		List<Department> departments = jpaService.findDepartments();
		return Results.success().result(departments);
	}
}
