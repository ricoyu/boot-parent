package com.loserico.boot.mongodb.repository;

import com.loserico.boot.mongodb.entity.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ricoy
 */
public interface PersonDao {
	
	Person savePerson(Person person);
	
	List<Person> getAllPerson();
	
	List<Person> getAllPersonPaginated(
			int pageNumber, int pageSize);
	
	Person findOneByName(String name);
	
	List<Person> findByName(String name);
	
	List<Person> findByBirthDateAfter(LocalDate date);
	
	List<Person> findByAgeRange(int lowerBound, int upperBound);
	
	List<Person> findByFavoriteBooks(String favoriteBook);
	
	void updateMultiplePersonAge();
	
	Person updateOnePerson(Person person);
	
	void deletePerson(Person person);
}