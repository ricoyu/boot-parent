package com.loserico.boot.mongodb.service;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-24 14:08
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface EmployeePropertyService {
	
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);
}
