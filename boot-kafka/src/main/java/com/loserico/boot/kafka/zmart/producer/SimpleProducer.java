package com.loserico.boot.kafka.zmart.producer;

import com.loserico.boot.kafka.zmart.model.PurchaseKey;
import com.loserico.boot.kafka.zmart.partitioner.PurchaseKeyPartitioner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * <p>
 * Copyright: (C), 2020-09-25 15:37
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class SimpleProducer {
	
	@SneakyThrows
	public static void main(String[] args) {
		System.out.println("22".hashCode() % 3);
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.100.104:9092");
		props.put("key.serializer", "com.loserico.boot.kafka.zmart.serializer.PurchaseKeySerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("acks", "1");
		props.put("retries", "3");
		props.put("compression.type", "snappy");
		props.put("partitioner.class", PurchaseKeyPartitioner.class.getName());
		
		PurchaseKey key = new PurchaseKey("22111你好ya16~~", new Date());
		
		try (Producer<PurchaseKey, String> producer = new KafkaProducer<>(props)){
			ProducerRecord<PurchaseKey, String> record = new ProducerRecord<>("purchase", key, "some value");
			Callback callback = (metadata, e) -> {
				if (e != null) {
					e.printStackTrace();;
				}
			};
			
			Future<RecordMetadata> sendFuture = producer.send(record, callback);
			
			RecordMetadata recordMetadata = sendFuture.get();
			log.info("发送到Topic:{}", recordMetadata.topic());
			log.info("发送到分区:{}", recordMetadata.partition());
			log.info("当前offset:{}", recordMetadata.offset());
		}
		
	}
}
