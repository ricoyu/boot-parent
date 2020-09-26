package com.loserico.boot.mongodb.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.loserico.boot.mongodb.entity.Student;
import com.loserico.boot.mongodb.entity.Student2;
import com.loserico.boot.mongodb.service.StudentService;
import com.loserico.json.jackson.JacksonUtils;
import com.loserico.mongo.dao.ScriptOperations;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-09-14 17:36
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private ScriptOperations scriptOperations;
	
	@Override
	public List<Student> queryStudents() {
		return scriptOperations.queryForList("{'grade.redis': 87}", Student.class);
	}
	
	@Override
	public long updateStudent() {
		UpdateResult updateResult = scriptOperations.updateMany( "student", "updateStudent.txt");
		return updateResult.getModifiedCount();
	}
	
	@Override
	public Student2 findOne() {
		return scriptOperations.findOne("{'name': 'John'}", Student2.class);
	}
	
	@Override
	public Object replaceOne() {
		JacksonUtils.objectMapper().configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		Student2 student2 =
				JacksonUtils.toObject("{'_id': null, 'name' : 'John', 'marks' : { 'english' : 100, 'maths' : 90 }, 'result' : 'success' }", Student2.class);
		return scriptOperations.replaceOne("student", "{'name': 'John'}", student2);	
	}
}
