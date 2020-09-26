package com.loserico.boot.mongodb.service;

import com.loserico.boot.mongodb.entity.Employee;
import com.loserico.common.lang.vo.Page;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-7-29 0029 13:42
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface EmployeeService {
	
	/**
	 * 查出所有雇员
	 * @return
	 */
	public List<Employee> findAll();
	
	public List<Employee> findAll(Page page);
	
	public Employee createEmployee(Employee employee);
	
	public List<Employee> saveAll(List<Employee> employees);
	
	public Employee findById(String id);
	
	public List<Employee> findByIds(String[] ids);
	
	public Employee ensureEntityExists(String id);
	
	void delete(Employee employee);
	
	void deleteByPk(String id);
	
	void deleteByPks(String... id);
	
	int deleteAll();
	
	public List<Employee> queryEmployee();
	
	List<Employee> queryEmployee(Employee queryVo, Page page);
}
