package com.loserico.es6.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * 通过transport方式连接
 * <p>
 * Copyright: (C), 2020/7/3 9:07
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Configuration
public class ElasticSearchManager implements InitializingBean {
	
	public TransportClient client;
	
	private Settings settings;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("对es进行初始化");
		init();
		log.info("es初始化完成");
	}
	
	@SneakyThrows
	public void init() {
		settings = Settings.builder().put("cluster.name", "es6-application").build();
		client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.100.101"), 9300))
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.100.102"), 9300))
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.100.103"), 9300));
	}
	
}
