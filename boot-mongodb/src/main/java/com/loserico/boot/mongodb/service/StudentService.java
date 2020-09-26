package com.loserico.boot.mongodb.service;

import com.loserico.boot.mongodb.entity.Student;
import com.loserico.boot.mongodb.entity.Student2;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-09-14 17:35
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface StudentService {
	
	/**
	 * 查询所有学生
	 * @return List<Student>
	 */
	public List<Student> queryStudents();
	
	long updateStudent();
	
	Student2 findOne();
	
	Object replaceOne();
}
