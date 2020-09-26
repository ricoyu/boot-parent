package com.loserico.boot.kafka.zmart.deserializer;

import com.loserico.boot.kafka.zmart.model.PurchaseKey;
import com.loserico.common.lang.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>
 * Copyright: (C), 2020-09-25 17:26
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class PurchaseKeyDeserializer implements Deserializer<PurchaseKey> {
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		log.info("留空, 不需要实现");
	}
	
	@Override
	public PurchaseKey deserialize(String topic, byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		
		ByteBuffer buf = ByteBuffer.wrap(data);
		
		int sizeOfCustomerId = buf.getInt();
		byte[] byteOfCustomerId = new byte[sizeOfCustomerId];
		buf.get(byteOfCustomerId);
		String customerId = new String(byteOfCustomerId, UTF_8);
		
		int sizeOfTransactionDate = buf.getInt();
		byte[] byteOfTransactionDate = new byte[sizeOfTransactionDate];
		buf.get(byteOfTransactionDate);
		String dateStr = new String(byteOfTransactionDate, UTF_8);
		Date transactionDate = DateUtils.parse(dateStr);
		
		return new PurchaseKey(customerId, transactionDate);
	}
	
	@Override
	public void close() {
		
	}
}
