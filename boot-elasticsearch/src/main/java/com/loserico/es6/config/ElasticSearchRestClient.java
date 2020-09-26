package com.loserico.es6.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Copyright: (C), 2020-7-7 0007 11:23
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Component
public class ElasticSearchRestClient {
	
	@Bean
	public RestHighLevelClient highLevelClient() {
		HttpHost httpHost = new HttpHost("192.168.100.101", 9200);
		HttpHost httpHost2 = new HttpHost("192.168.100.102", 9200);
		HttpHost httpHost3 = new HttpHost("192.168.100.103", 9200);
		
		return new RestHighLevelClient(RestClient.builder(new HttpHost[]{httpHost, httpHost2, httpHost3}));
	}
}
