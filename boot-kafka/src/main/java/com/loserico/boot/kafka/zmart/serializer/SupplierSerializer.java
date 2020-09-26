package com.loserico.boot.kafka.zmart.serializer;

import com.loserico.boot.kafka.zmart.model.Supplier;
import com.loserico.common.lang.utils.DateUtils;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>
 * Copyright: (C), 2020-09-25 16:22
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class SupplierSerializer implements Serializer<Supplier> {
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}
	
	@Override
	public byte[] serialize(String topic, Supplier data) {
		int sizeOfName;
		int sizeOfDate;
		
		byte[] serializedName;
		byte[] serializedDate;
		
		try {
			if (data == null) {
				return null;
			}
			
			serializedName = data.getName() == null ? new byte[0] : data.getName().getBytes(UTF_8);
			sizeOfName = serializedName.length;
			
			serializedDate = data.getStartDate() == null ? new byte[0] : DateUtils.format(data.getStartDate()).getBytes(UTF_8);
			sizeOfDate = serializedDate.length;
			
			ByteBuffer buf = ByteBuffer.allocate(4 + 4 + sizeOfName + 4 + sizeOfDate);
			buf.putInt(data.getID());
			buf.putInt(sizeOfName);
			buf.put(serializedName);
			buf.putInt(sizeOfDate);
			buf.put(serializedDate);
			
			return buf.array();
		} catch (Exception e) {
			throw new SerializationException("Error when serializing Supplier to byte[]");
		}
	}
	
	@Override
	public void close() {
		
	}
}
