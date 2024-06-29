package com.loserico.boot.kafka.config;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

/**
 * https://blog.csdn.net/xiaobuding007/article/details/82699758
 * <p>
 * Copyright: (C), 2020-7-30 0030 18:02
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class KafkaConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.consumer.enable-auto-commit}")
	private Boolean autoCommit;
	
	@Value("${spring.kafka.consumer.auto-commit-interval}")
	private Integer autoCommitInterval;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Value("${spring.kafka.consumer.max-poll-records}")
	private Integer maxPollRecords;
	
	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;
	
	@Value("${spring.kafka.producer.retries}")
	private Integer retries;
	
	@Value("${spring.kafka.producer.batch-size}")
	private Integer batchSize;
	
	@Value("${spring.kafka.producer.buffer-memory}")
	private Integer bufferMemory;
	
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = Maps.newHashMap();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "testbatchGroup");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 120000);
		props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 180000);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return props;
	}
	
	@Bean
	public KafkaListenerContainerFactory<?> batchFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
		//设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
		factory.setBatchListener(true);
		return factory;
	}
}
