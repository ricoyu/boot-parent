package com.loserico.boot.mongodb.repository;

import com.loserico.boot.mongodb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-24 16:47
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	
	public List<Employee> findEmployeeByName(String name);
	
}
