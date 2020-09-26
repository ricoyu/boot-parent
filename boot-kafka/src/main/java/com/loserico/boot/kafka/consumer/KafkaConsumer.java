package com.loserico.boot.kafka.consumer;

import com.loserico.json.jackson.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Copyright: (C), 2020-7-22 0022 18:44
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Component
public class KafkaConsumer {
	
	@KafkaListener(topics = "test")
	public void listen(ConsumerRecord<?, ?> record) {
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		if (kafkaMessage.isPresent()) {
			Object message = kafkaMessage.get();
			log.info("<<<<<< record =" + record);
			log.info("<<<<<< message =" + message);
		}
	}
	
	@KafkaListener(topics = "test", groupId = "testGroup")
	public void testLisnter(ConsumerRecord<String, String> record) {
		String value = record.value();
		log.info(value);
		log.info(JacksonUtils.toJson(record));
	}
	
	@KafkaListener(topics = "test", containerFactory = "batchFactory")
	public void batchLisnter(List<ConsumerRecord<String, String>> records) {
		log.info(">>>>>>>>>>>{}<<<<<<<<<<", records.size());
		for (ConsumerRecord<String, String> record : records) {
			String value = record.value();
			log.info(value);
			log.info(JacksonUtils.toJson(record));
		}
	}
	
	/**
	 * concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
	 *
	 * @param record
	 */
	@KafkaListener(groupId = "group2", topicPartitions = {
			@TopicPartition(topic = "topic1", partitions = {"0", "1"}),
			@TopicPartition(topic = "topic2", partitions = {"0"}, partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
		
	}, concurrency = "6")
	public void multiTopicListener(ConsumerRecord<String, String> record) {
		
	}
}
