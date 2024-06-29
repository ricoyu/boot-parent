package com.loserico.boot.kafka.zmart.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Copyright: (C), 2020-09-28 14:20
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class ThreadedConsumerExample {
	
	private volatile boolean doneConsuming = false;
	
	private int numberPartitions;
	
	private ExecutorService executorService;
	
	public ThreadedConsumerExample(int numberPartitions) {
		this.numberPartitions = numberPartitions;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadedConsumerExample consumerExample = new ThreadedConsumerExample(2);
		consumerExample.startConsuming();
		Thread.sleep(60000); //Run for one minute
		consumerExample.stopConsuming();
	}
	
	public void startConsuming() {
		executorService = Executors.newFixedThreadPool(numberPartitions);
		Properties properties = getConsumerProps();
		
		for (int i = 0; i < numberPartitions; i++) {
			Runnable consumerThread = getConsumerThread(properties);
			executorService.submit(consumerThread);
		}
	}
	
	public void stopConsuming() throws InterruptedException {
		doneConsuming = true;
		executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
		executorService.shutdown();
	}
	
	private Runnable getConsumerThread(Properties properties) {
		return () -> {
			Consumer<String, String> consumer = null;
			try {
				consumer = new KafkaConsumer<String, String>(properties);
				consumer.subscribe(Collections.singletonList("test-topic"));
				while (!doneConsuming) {
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
					for (ConsumerRecord<String, String> record : records) {
						String message = String.format("Consumed: key = %s value = %s with offset = %d partition = %d", record.key(),
								record.value(),
								record.offset(),
								record.partition());
						System.out.println(message);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (consumer != null) {
					consumer.close();
				}
			}
		};
	}
	
	private Properties getConsumerProps() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "192.168.100.104:9092");
		properties.put("group.id", "simple-consumer-example");
		properties.put("auto.offset.reset", "earliest");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "3000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		return properties;
	}
}
