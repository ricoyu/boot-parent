package com.loserico.boot.kafkastreaming.util.serializer;

import bbejeck.collectors.FixedSizePriorityQueue;
import bbejeck.util.serializer.FixedSizePriorityQueueAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020-09-30 10:32
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class JsonSerializer<T> implements Serializer<T> {
	
	private Gson gson;
	
	public JsonSerializer() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(FixedSizePriorityQueue.class, new FixedSizePriorityQueueAdapter().nullSafe());
		gson = builder.create();
	}
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}
	
	@Override
	public byte[] serialize(String topic, T data) {
		return gson.toJson(data).getBytes(Charset.forName("UTF-8"));
	}
	
	@Override
	public void close() {
		
	}
}
