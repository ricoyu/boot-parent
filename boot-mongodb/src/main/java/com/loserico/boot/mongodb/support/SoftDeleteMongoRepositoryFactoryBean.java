package com.loserico.boot.mongodb.support;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/**
 * <p>
 * Copyright: (C), 2020-08-24 16:43
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class SoftDeleteMongoRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends MongoRepositoryFactoryBean<T, S, ID> {
	
	public SoftDeleteMongoRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}
	
	@Override
	protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
		return new SoftDeleteMongoRepositoryFactory(operations);
	}
}
