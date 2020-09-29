package com.loserico.boot.kafkastreaming.beans;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 一个srcNode 负责从kafka读取消息
 * 一个processorNode负责将消息文字转成大写
 * 最后一个sinknode负责将大写文字写回kafka
 * <p>
 * Copyright: (C), 2020-09-28 15:59
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Component
public class KafkaStreamsYellingApp {
	
	@SneakyThrows
	public static void start() {
		//生成数据丢到kafka中
		//MockDataProducer.produceRandomTextData();
		//Properties for configuring the Kafka Streams program
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "yelling_app_id");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.104:9092");
		
		StreamsConfig streamsConfig = new StreamsConfig(props);
		
		//Creates the Serdes used to serialize/deserialize keys and values
		Serde<String> stringSerde = Serdes.String();
		
		//Creates the StreamsBuilder instance used to construct the processor topology
		StreamsBuilder builder = new StreamsBuilder();
		
		//Creates the actual stream with a source topic to read from (the parent node in the graph)
		KStream<String, String> simpleFirstStream = builder.stream("src-topic", Consumed.with(stringSerde, stringSerde));
		KStream<String, String> upperCasedStream = simpleFirstStream.mapValues((s) -> {
			log.info("upperCasedStream从上游获取到原始消息: {}", s);
			return s.toUpperCase();
		});
		
		//Writes the transformed output to another topic (the sink node in the graph)
		upperCasedStream.to("out-topic", Produced.with(stringSerde, stringSerde));
		upperCasedStream.print(Printed.<String, String>toSysOut().withLabel("Yelling App"));
		
		KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), streamsConfig);
		log.info("Hello World Yelling App Started");
		//Kicks off the Kafka Streams threads
		kafkaStreams.start();
		//Thread.sleep(35000);
		//log.info("Shutting down the Yelling APP now");
		//kafkaStreams.close();
		
	}
}
