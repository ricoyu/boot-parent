package com.loserico.boot.kafka.producer;

import com.loserico.boot.kafka.beans.Order;
import com.loserico.json.jackson.JacksonUtils;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * Copyright: (C), 2020-7-25 0025 21:19
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class MsgProducer {
	
	@SneakyThrows
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.101:9092,192.168.100.102:9092,192.168.100.103:9092");
		/*
		 * 发出消息持久化机制参数
		 *
		 * acks=0
		 * 表示producer不需要等待任何broker确认收到消息的回复, 就可以继续发送下一条消息。性能最高, 但是最容易丢消息。
		 * acks=1
		 * 至少要等待leader已经成功将数据写入本地log, 但是不需要等待所有follower是否成功写入。就可以继续发送下一条消息。
		 *  这种情况下, 如果follower没有成功备份数据, 而此时leader又挂掉, 则消息会丢失。
		 * acks=‐1或all
		 * 这意味着leader需要等待所有备份(min.insync.replicas配置的备份个数)都成功写入日志, 这种策略会保证只要有一个备份存活就不会丢失数据。
		 * 这是最强的数据保证。一般除非是金融级别, 或跟钱打交道的场景才会使用这种配置。
		 */
		properties.put(ProducerConfig.ACKS_CONFIG, "1");
		/*
		 * 发送失败会重试, 默认重试间隔100ms, 重试能保证消息发送的可靠性, 但是也可能造成消息重复发送, 比如网络抖动, 所以需要在接收者那边做好消息接收的幂等性处理
		 */
		properties.put(ProducerConfig.RETRIES_CONFIG, 3);
		//重试间隔设置
		properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);
		/*
		 * 设置发送消息的本地缓冲区, 如果设置了该缓冲区, 消息会先发送到本地缓冲区, 可以提高消息发送性能, 默认值是33554432, 即32MB
		 */
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		/*
		 * kafka本地线程会从缓冲区取数据, 批量发送到broker, 设置批量发送消息的大小, 默认值是16384, 即16kb, 就是说一个batch满了16kb就发送出去
		 */
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		/*
		 * 默认值是0, 意思就是消息必须立即被发送, 但这样会影响性能
		 * 一般设置100毫秒左右, 就是说这个消息发送完后会进入本地的一个batch, 如果100毫秒内, 这个batch满16kb就会随batch一起被发送出
		 * 如果100毫秒内, batch没满, 那么也必须把消息发送出去, 不能让消息的发送延迟时间太长
		 */
		properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
		/*
		 * 把发送的key从字符串序列化为字节数组
		 */
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		/*
		 * 把发送消息value从字符串序列化为字节数组
		 */
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		Producer<String, String> producer = new KafkaProducer<String, String>(properties);
		CountDownLatch countDownLatch = new CountDownLatch(5);
		for (int i = 1; i <= 5; i++) {
			Order order = new Order(i, i + 100, 1, 1000.00);
			//指定发送分区
			ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test", 0, order.getOrderId() + "", JacksonUtils.toJson(order));
			//未指定发送分区, 具体发送的分区计算公式: hash(key)%partitionNum
			//ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("my‐replicated‐topic", order.getOrderId().toString(), JacksonUtils.toJson(order));
			
			//等待消息发送成功的同步阻塞方法
			/*RecordMetadata metadata = producer.send(producerRecord).get();
			System.out.println("同步方式发送消息结果：" + "topic‐" + metadata.topic() + "|partition‐" + metadata.partition() + "|offset‐" + metadata.offset());*/
			
			//异步方式发送消息
			producer.send(producerRecord, (metadata, e) -> {
				countDownLatch.countDown();
				if (e != null) {
					System.err.println("发送消息失败：" + e.getStackTrace());
				}
				if (metadata != null) {
					System.out.println("异步发送消息结果: " + "Topic-" + metadata.topic() + "|Partition=" + metadata.partition() + "|offset-" + metadata.offset());
				}
			});
		}
		
		countDownLatch.await();
		producer.close();
	}
}
