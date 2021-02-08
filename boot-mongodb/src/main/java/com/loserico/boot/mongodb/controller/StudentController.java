package com.loserico.boot.mongodb.controller;

import com.loserico.boot.mongodb.entity.Student2;
import com.loserico.boot.mongodb.service.StudentService;
import com.loserico.common.lang.vo.Result;
import com.loserico.common.lang.vo.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Copyright: (C), 2020-09-14 17:37
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/by-grade")
	public Result queryStudents() {
		return Results.success().result(studentService.queryStudents());
	}
	
	@PostMapping("/update")
	public Result updateStudent() {
		studentService.updateStudent();
		Student2 student = studentService.findOne();
		return Results.success().result(student);
	}
	
	@PostMapping("/replace")
	public Result replaceStudent() {
		Object o = studentService.replaceOne();
		return Results.success().result(o);
	}
}
