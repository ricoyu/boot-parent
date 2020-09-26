package com.loserico.boot.mongodb.repository.impl;

import com.loserico.boot.mongodb.entity.Person;
import com.loserico.boot.mongodb.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-08-20 10:22
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Repository
public class PersonDaoImpl implements PersonDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Person savePerson(Person person) {
		return null;
	}
	
	@Override
	public List<Person> getAllPerson() {
		return null;
	}
	
	@Override
	public List<Person> getAllPersonPaginated(int pageNumber, int pageSize) {
		return null;
	}
	
	@Override
	public Person findOneByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		
		return mongoTemplate.findOne(query, Person.class);
	}
	
	@Override
	public List<Person> findByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.find(query, Person.class);
	}
	
	@Override
	public List<Person> findByBirthDateAfter(LocalDate date) {
		return null;
	}
	
	@Override
	public List<Person> findByAgeRange(int lowerBound, int upperBound) {
		return null;
	}
	
	@Override
	public List<Person> findByFavoriteBooks(String favoriteBook) {
		return null;
	}
	
	@Override
	public void updateMultiplePersonAge() {
		
	}
	
	@Override
	public Person updateOnePerson(Person person) {
		return null;
	}
	
	@Override
	public void deletePerson(Person person) {
		
	}
}
