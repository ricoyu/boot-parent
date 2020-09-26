package com.loserico.boot.mongodb.service.impl;

import com.loserico.boot.mongodb.service.EmployeePropertyService;
import com.loserico.mongo.dao.CriteriaOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-24 14:09
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class EmployeePropertyServiceImpl implements EmployeePropertyService {
	
	@Autowired
	private CriteriaOperations criteriaOperations;
	
	@Override
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
		return criteriaOperations.findByProperty(entityClass, propertyName, value);
	}
}
