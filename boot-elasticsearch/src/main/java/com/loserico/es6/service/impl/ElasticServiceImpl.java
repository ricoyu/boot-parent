package com.loserico.es6.service.impl;

import com.loserico.es6.config.ElasticSearchManager;
import com.loserico.es6.service.ElasticService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020/7/3 9:56
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
public class ElasticServiceImpl implements ElasticService {
	
	@Autowired
	private ElasticSearchManager elasticSearchManager;
	private XContentBuilder xContentBuilder;
	
	@Override
	public boolean add(Map<String, Object> doc, String index, String type) throws Exception {
		if (doc == null) {
			return false;
		}
		
		try {
			XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject();
			Iterator<String> iterator = doc.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = doc.get(key);
				if (value instanceof Integer) {
					xContentBuilder.field(key, Integer.valueOf(value.toString()));
				} else if (value instanceof Long) {
					xContentBuilder.field(key, Long.valueOf(value.toString()));
				} else if (value instanceof String) {
					xContentBuilder.field(key, value.toString());
				} else {
					xContentBuilder.field(key, value);
				}
			}
			xContentBuilder.endObject();
			elasticSearchManager.client.prepareIndex(index, type).setSource(xContentBuilder).get();
			return true;
		} catch (Exception e) {
			log.error("数据插入es失败,index={},type={}", index, type, e);
		}
		
		return false;
	}
	
	@Override
	public boolean adds(List<Map<String, Object>> docs) {
		return false;
	}
	
	@Override
	public boolean addBulkIn(List<Map<String, Object>> datas, String index, String type) {
		try {
			BulkRequestBuilder bulkRequestBuilder = elasticSearchManager.client.prepareBulk();
			for (Map<String, Object> data : datas) {
				bulkRequestBuilder.add(elasticSearchManager.client.prepareIndex(index, type, data.get("bookId").toString()).setSource(data));
			}
			
			BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				log.info(bulkResponse.status().toString());
				log.info(bulkResponse.buildFailureMessage());
			}
			return true;
		} catch (Exception e) {
			log.error("数据插入es失败,index={},type={}", index, type, e);
		}
		return false;
	}
	
	@Override
	public boolean delete(String index) {
		DeleteRequest deleteRequest = new DeleteRequest(index);
		elasticSearchManager.client.delete(deleteRequest);
		return true;
	}
}
