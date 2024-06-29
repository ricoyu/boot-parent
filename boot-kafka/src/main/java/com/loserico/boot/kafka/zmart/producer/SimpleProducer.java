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
		//逗号隔开的 host:port
		props.put("bootstrap.servers", "192.168.100.104:9092");
		//控制kafka将key/value转成byte[]的方式
		props.put("key.serializer", "com.loserico.boot.kafka.zmart.serializer.PurchaseKeySerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		/*
		 * 0 Sender 不等带broker ack就认为发送成功了
		 * 1 等待Leader broker ack 后认为发送成功了
		 * all 等待Leader broker 和所有的Follower brokers都声明ack后才认为发送成功了
		 */
		props.put("acks", "1");
		/*
		 * 发送失败后重试次数
		 * 如果要保证前后两个batch发送的顺序性, 比如先发第一个batch, 失败了, 但是第二个batch发送成功了, 第一个重发的时候也成功了, 所以实际上第二个batch在跑到前面去了
		 * 要保证顺序的话得设置 max.in.flight.requests.per.connection=1
		 */
		props.put("retries", "3");
		props.put("compression.type", "snappy");
		props.put("partitioner.class", PurchaseKeyPartitioner.class.getName());
		
		PurchaseKey key = new PurchaseKey("22111你好ya16~~", new Date());
		
		try (Producer<PurchaseKey, String> producer = new KafkaProducer<>(props)) {
			ProducerRecord<PurchaseKey, String> record = new ProducerRecord<>("transactions", key, "{\"item\":\"book\",\"price\":10.99}");
			/*
			 * send是异步的, Leader broker ack这条消息后, 这个回调函数被调用
			 */
			Callback callback = (metadata, e) -> {
				if (e != null) {
					e.printStackTrace();
					;
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
