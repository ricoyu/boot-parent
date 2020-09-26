package com.loserico.boot.kafka.zmart.deserializer;

import com.loserico.boot.kafka.zmart.model.Supplier;
import com.loserico.common.lang.utils.DateUtils;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>
 * Copyright: (C), 2020-09-25 16:46
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class SupplierDeserializer implements Deserializer<Supplier> {
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}
	
	@SneakyThrows
	@Override
	public Supplier deserialize(String topic, byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		
		ByteBuffer buf = ByteBuffer.wrap(data);
		int id = buf.getInt();
		
		int sizeOfName = buf.getInt();
		byte[] nameBytes = new byte[sizeOfName];
		buf.get(nameBytes);
		
		String name = new String(nameBytes, UTF_8);
		
		int sizeOfDate = buf.getInt();
		byte[] dateBytes = new byte[sizeOfDate];
		buf.get(dateBytes);
		
		String dateStr = new String(dateBytes, UTF_8);
		Date date = DateUtils.parse(dateStr);
		
		return new Supplier(id, name, date);
	}
	
	@Override
	public void close() {
		
	}
}
