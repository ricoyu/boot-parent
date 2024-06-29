package com.loserico.boot.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.util.Arrays.asList;

/**
 * <p>
 * Copyright: (C), 2020-7-26 0026 9:59
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class MsgConsumer {
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.101:9092,192.168.100.102:9092,192.168.100.103:9092");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
		
		/*
		 * 是否自动提交offset
		 * 即当前consumer消费到的offset写到kafka的一个Topic(__consumer_offsets)里面, key是consumerGroupId+topic+分区号
		 * 
		 * 自动提交的好处是效率高一点
		 * 但是有一个问题是:假设现在消费1000条消息, 如果在消费了一半后, 消费者挂了, 那么此时自动提交就做不了, 下次还得重新消费
		 * 但如果是手动提交, 消费1000条消息的话, 可以消费一部分后就提交一次
		 */
		//properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		//自动提交offset的间隔时间
		//properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		
		/*
		 * 心跳时间, 服务端broker通过心跳确认Consumer是否故障, 如果发现故障, 就会通过心跳下发rebalance指令
		 * 给其他的consumer, 通知他们进行rebalance操作, 这个时间可以稍微短一点
		 */
		properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
		
		/*
		 * 服务端Broker多久感知不到一个consumer的心跳就认为它故障了, 默认是10秒
		 */
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10 * 1000);
		
		/*
		 * 如果两次poll操作间隔超过了这个时间, Broker就会认为这个Consumer处理能力太弱,
		 * 会将其剔除消费者组, 将分区分配给其他Consumer消费
		 */
		properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30 * 1000);
		
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		
		//消费主题
		String topic = "test";
		
		/*
		 * 这种方式不指定消费的分区, 那么消费者会轮询消费该主题的所有分区
		 */
		consumer.subscribe(asList(topic));
		
		/*
		 * 消费指定分区
		 */
		//consumer.assign(asList(new TopicPartition(topic, 0)));
		
		/*
		 * 消息回溯消费, 从头开始消费
		 */
		/*consumer.assign(asList(new TopicPartition(topic, 0)));
		consumer.seekToBeginning(asList(new TopicPartition(topic, 0)));*/
		
		/*
		 * 指定offset消费
		 */
		consumer.assign(asList(new TopicPartition(topic, 0)));
		consumer.seek(new TopicPartition(topic, 0), 10);
		
		/*
		 * 从指定时间点开始消费
		 */
		Map<TopicPartition, Long> map = new HashMap<>();
		List<PartitionInfo> topicPartitions = consumer.partitionsFor(topic);
		//从半小时前开始消费
		long fetchDataTime = 1000 * 60 * 30;
		//把这个时间点和这个Topic的每个分区绑定
		for (PartitionInfo partitionInfo : topicPartitions) {
			map.put(new TopicPartition(topic, partitionInfo.partition()), fetchDataTime);
		}
		//计算得到每个分区在半小时前的offset值
		Map<TopicPartition, OffsetAndTimestamp> parMap = consumer.offsetsForTimes(map);
		for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : parMap.entrySet()) {
			TopicPartition key = entry.getKey();
			OffsetAndTimestamp value = entry.getValue();
			
			if (key == null || value == null) {
				continue;
			}
			
			Long offset = value.offset();
			System.out.println("partition-" + key.partition() + "|offset-" + offset);
			System.out.println();
			
			//根据timestamp确定offset
			if (value != null) {
				//没有这行代码会导致下面的报错信息
				consumer.assign(asList(key));
				//从这个offset开始消费
				consumer.seek(key, offset);
			}
		}
		
		while (true) {
			/*
			 * poll() API 是拉取消息的长轮询, 主要是判断consumer是否还活着, 只要我们持续调用poll(), 
			 * 消费者就会存活在自己所在的group中, 并且持续消费指定partition的消息。
			 * 底层是这么做的: 消费者向server持续发送心跳, 如果一个时间段(session.timeout.ms) consumer挂掉或是不能发送心跳, 
			 * 这个消费者会被认为是挂掉了, 这个Partition也会被重新分配给其他consumer
			 */
			ConsumerRecords<String, String> records = consumer.poll(10000);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("收到消息: offset=%d, key=%s, value=%s%n", record.offset(), record.key(), record.value());
			}
			
			if (records.count() > 0) {
				//提交offset
				consumer.commitSync();
			}
		}
	}
}
