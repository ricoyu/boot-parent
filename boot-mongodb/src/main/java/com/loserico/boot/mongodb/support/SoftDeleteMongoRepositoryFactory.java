package com.loserico.boot.mongodb.support;

import com.loserico.boot.mongodb.strategy.SoftDeleteMongoQueryLookupStrategy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;

import java.util.Optional;

/**
 * https://blog.rpuch.com/2019/10/27/spring-data-mongo-soft-delete-repositories.html
 * <p>
 * Copyright: (C), 2020-08-24 16:36
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class SoftDeleteMongoRepositoryFactory extends MongoRepositoryFactory {
	
	private final MongoOperations mongoOperations;
	
	public SoftDeleteMongoRepositoryFactory(MongoOperations mongoOperations) {
		super(mongoOperations);
		this.mongoOperations = mongoOperations;
	}
	
	@Override
	protected Optional<QueryLookupStrategy> getQueryLookupStrategy(QueryLookupStrategy.Key key,
	                                                               QueryMethodEvaluationContextProvider evaluationContextProvider) {
		Optional<QueryLookupStrategy> optStrategy = super.getQueryLookupStrategy(key, evaluationContextProvider);
		return optStrategy.map(this::createSoftDeleteQueryLookupStrategy);
	}
	
	private SoftDeleteMongoQueryLookupStrategy createSoftDeleteQueryLookupStrategy(QueryLookupStrategy strategy) {
		return new SoftDeleteMongoQueryLookupStrategy(strategy, mongoOperations);
	}
}
