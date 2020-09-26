package com.loserico.boot.mongodb.service.impl;

import com.loserico.boot.mongodb.entity.Employee;
import com.loserico.boot.mongodb.entity.Person;
import com.loserico.boot.mongodb.service.EmployeeService;
import com.loserico.common.lang.vo.OrderBean;
import com.loserico.common.lang.vo.Page;
import com.loserico.mongo.dao.EntityOperations;
import com.loserico.mongo.dao.ScriptOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

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
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private EntityOperations entityOperations;
	
	@Autowired
	private ScriptOperations scriptOperations;
	
	@Override
	public List<Employee> findAll() {
		//return mongoTemplate.findAll(Employee.class);
		return entityOperations.findAll(Employee.class);
	}
	
	@Override
	public List<Employee> findAll(Page page) {
		return entityOperations.findAll(Employee.class, page);
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		//return mongoTemplate.save(employee);
		return entityOperations.save(employee);
	}
	
	@Override
	public List<Employee> saveAll(List<Employee> employees) {
		return entityOperations.save(employees);
	}
	
	@Override
	public Employee findById(String id) {
		//return mongoTemplate.findById(id, Employee.class);
		return entityOperations.get(Employee.class, id);
	}
	
	@Override
	public List<Employee> findByIds(String[] ids) {
		return entityOperations.getMulti(Employee.class, ids);
	}
	
	@Override
	public Employee ensureEntityExists(String id) {
		return entityOperations.ensureEntityExists(Employee.class, id);
	}
	
	@Override
	public void delete(Employee employee) {
		entityOperations.delete(employee);
	}
	
	@Override
	public void deleteByPk(String id) {
		entityOperations.deleteByPk(Employee.class, id);
	}
	
	@Override
	public void deleteByPks(String... ids) {
		entityOperations.deleteByPk(Employee.class, asList(ids));
	}
	
	@Override
	public int deleteAll() {
		return entityOperations.deleteAll(Employee.class);
	}
	
	@Override
	public List<Employee> queryEmployee() {
		Person person = new Person();
		person.setName("三少爷");
		Page page = new Page();
		page.setCurrentPage(1);
		page.setPageSize(2);
		page.addOrder("name", OrderBean.ORDER_BY.DESC);
		//int[] salarys = {10000, 3000};
		List<Integer> salarys = Arrays.asList(10000, 8000);
		//return scriptOperations.queryForList("{'salary': {$in: #{salarys}}}", Employee.class, salarys, page);
		return scriptOperations.queryForList("queryEmployee", Employee.class, salarys, page);
	}
	
	@Override
	public List<Employee> queryEmployee(Employee employee, Page page) {
		return scriptOperations.queryForList("{'job': '#{job}'}", Employee.class, employee, page);
	}

}
