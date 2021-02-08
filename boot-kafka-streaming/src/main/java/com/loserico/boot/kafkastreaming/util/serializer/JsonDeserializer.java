package com.loserico.boot.kafkastreaming.util.serializer;

import bbejeck.collectors.FixedSizePriorityQueue;
import bbejeck.util.serializer.FixedSizePriorityQueueAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020-09-30 10:48
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class JsonDeserializer<T> implements Deserializer<T> {
	
	private Gson gson;
	private Class<?> deserializedClass;
	private Type reflectionTypeToken;
	
	public JsonDeserializer() {
	}
	
	public JsonDeserializer(Class<?> deserializedClass) {
		this.deserializedClass = deserializedClass;
		init();
	}
	
	public JsonDeserializer(Type reflectionTypeToken) {
		this.reflectionTypeToken = reflectionTypeToken;
		init();
	}
	
	private void init() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(FixedSizePriorityQueue.class, new FixedSizePriorityQueueAdapter().nullSafe());
		gson = builder.create();
	}
	
	@Override
	public void configure(Map configs, boolean isKey) {
		if (deserializedClass == null) {
			deserializedClass = (Class<T>) configs.get("serializedClass");
		}
	}
	
	@Override
	public T deserialize(String topic, byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		
		Type deserializeFrom = deserializedClass != null ? deserializedClass : reflectionTypeToken; 
		return gson.fromJson(new String(bytes),deserializeFrom);
	}
	
	@Override
	public void close() {
		
	}
}
