package com.loserico.boot.mongodb.repository;

import com.loserico.boot.mongodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Copyright: (C), 2020-7-27 0027 16:40
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	public Customer findByFirstName(String firstName);
	
	public List<Customer>findByLastName(String lastName);
}
