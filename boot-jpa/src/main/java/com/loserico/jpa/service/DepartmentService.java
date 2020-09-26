package com.loserico.jpa.service;

import com.loserico.jpa.entity.Department;
import com.loserico.orm.dao.EntityOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020/4/14 17:03
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class DepartmentService {
	
	@Autowired
	private EntityOperations entityOperations;
	
	public List<Department> findAll() {
		return entityOperations.findAll(Department.class);
	}
}
