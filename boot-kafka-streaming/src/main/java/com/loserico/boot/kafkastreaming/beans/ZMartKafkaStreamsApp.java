package com.loserico.boot.kafkastreaming.beans;

import bbejeck.model.Purchase;
import bbejeck.model.PurchasePattern;
import bbejeck.model.RewardAccumulator;
import com.loserico.boot.kafkastreaming.util.StreamsSerdes;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.MAX_VALUE;

/**
 * <p>
 * Copyright: (C), 2020-09-30 10:15
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Component
public class ZMartKafkaStreamsApp {
	
	@SneakyThrows
	public static void main(String[] args) {
		StreamsConfig streamsConfig = new StreamsConfig(getProperties());
		
		Serde<Purchase> purchaseSerde = StreamsSerdes.PurchaseSerde();
		Serde<PurchasePattern> purchasePatternSerde = StreamsSerdes.PurchasePatternSerde();
		Serde<RewardAccumulator> rewardAccumulatorSerde = StreamsSerdes.RewardAccumulatorSerde();
		Serde<String> stringSerde = Serdes.String();
		
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		/*
		 * 这一行代码创建了两个node:
		 * 一个是source node, 负责从topic: transactions 读入
		 * 第二个是masking node, 负责信用卡号脱敏(mapValues方法创建masking node)
		 */
		KStream<String, Purchase> purchaseKStream = streamsBuilder.stream("transactions", Consumed.with(stringSerde, purchaseSerde))
				.mapValues(p -> Purchase.builder(p).maskCreditCard().build());
		
		//patterns node
		KStream<String, PurchasePattern> patternKStream = purchaseKStream.mapValues(purchase -> PurchasePattern.builder(purchase).build());
		patternKStream.print(Printed.<String, PurchasePattern>toSysOut().withLabel("patterns"));
		
		//定义了patterns sink 节点
		patternKStream.to("patterns", Produced.with(stringSerde, purchasePatternSerde));
		
		//定义了rewards 节点
		KStream<String, RewardAccumulator> rewardsKStream  = purchaseKStream.mapValues(p -> RewardAccumulator.builder(p).build());
		rewardsKStream.print(Printed.<String, RewardAccumulator>toSysOut().withLabel("rewards"));
		
		//定义了rewards sink 节点
		rewardsKStream.to("rewards", Produced.with(stringSerde, rewardAccumulatorSerde));
		purchaseKStream.print(Printed.<String, Purchase>toSysOut().withLabel("purchases"));
		
		//定义了purchases sink 节点
		purchaseKStream.to("purchases", Produced.with(stringSerde,purchaseSerde));
		
		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), streamsConfig);
		log.info("ZMart First Kafka Streams Application Started");
		kafkaStreams.start();
		TimeUnit.MILLISECONDS.sleep(MAX_VALUE);
		log.info("Shutting down the Kafka Streams Application now");
		kafkaStreams.close();
		
	}
	
	private static Properties getProperties() {
		Properties props = new Properties();
		props.put(StreamsConfig.CLIENT_ID_CONFIG, "FirstZmart-Kafka-Streams-Client");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "zmart-purchases");
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "FirstZmart-Kafka-Streams-App");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.104:9092");
		props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
		props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
		return props;
	}
}
