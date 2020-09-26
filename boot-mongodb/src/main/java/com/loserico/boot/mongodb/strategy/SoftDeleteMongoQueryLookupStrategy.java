package com.loserico.boot.mongodb.strategy;

import com.loserico.boot.mongodb.annotation.SeesSoftlyDeletedRecords;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.ConvertingParameterAccessor;
import org.springframework.data.mongodb.repository.query.PartTreeMongoQuery;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;

import java.lang.reflect.Method;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * <p>
 * Copyright: (C), 2020-08-24 16:23
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class SoftDeleteMongoQueryLookupStrategy implements QueryLookupStrategy {
	
	private final QueryLookupStrategy strategy;
	
	private final MongoOperations mongoOperations;
	
	public SoftDeleteMongoQueryLookupStrategy(QueryLookupStrategy strategy, MongoOperations mongoOperations) {
		this.strategy = strategy;
		this.mongoOperations = mongoOperations;
	}
	
	@Override
	public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
		RepositoryQuery repositoryQuery = strategy.resolveQuery(method, metadata, factory, namedQueries);
		
		// revert to the standard behavior if requested
		if (method.getAnnotation(SeesSoftlyDeletedRecords.class) != null) {
			return repositoryQuery;
		}
		
		if (!(repositoryQuery instanceof PartTreeMongoQuery)) {
			return repositoryQuery;
		}
		
		PartTreeMongoQuery partTreeQuery = (PartTreeMongoQuery) repositoryQuery;
		
		return new SoftDeletePartTreeMongoQuery(partTreeQuery);
	}
	
	private Criteria notDeleted() {
		return new Criteria().orOperator(
				where("deleted").exists(false),
				where("deleted").is(false)
		);
	}
	
	private class SoftDeletePartTreeMongoQuery extends PartTreeMongoQuery {
		
		SoftDeletePartTreeMongoQuery(PartTreeMongoQuery partTreeMongoQuery) {
			super(partTreeMongoQuery.getQueryMethod(), mongoOperations);
		}
		
		@Override
		protected Query createQuery(ConvertingParameterAccessor accessor) {
			Query query = super.createQuery(accessor);
			return withNotDeleted(query);
		}
		
		@Override
		protected Query createCountQuery(ConvertingParameterAccessor accessor) {
			Query query = super.createCountQuery(accessor);
			return withNotDeleted(query);
		}
		
		private Query withNotDeleted(Query query) {
			return query.addCriteria(notDeleted());
		}
	}
	
}
