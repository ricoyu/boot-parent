package com.loserico.boot.kafka.zmart.serializer;

import com.loserico.boot.kafka.zmart.model.PurchaseKey;
import com.loserico.common.lang.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>
 * Copyright: (C), 2020-09-25 17:04
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class PurchaseKeySerializer implements Serializer {
	
	@Override
	public void configure(Map configs, boolean isKey) {
		log.info("留空, 不需要实现");
	}
	
	@Override
	public byte[] serialize(String topic, Object data) {
		if (data == null) {
			return new byte[0];
		}
		
		PurchaseKey purchaseKey = (PurchaseKey) data;
		byte[] customerIdBytes = purchaseKey.getCustomerId() == null ? new byte[0] : purchaseKey.getCustomerId().getBytes(UTF_8);
		int customerIdSize = customerIdBytes.length;
		
		byte[] transactionDateBytes =
				purchaseKey.getTransactionDate() == null ? new byte[0] : DateUtils.format(purchaseKey.getTransactionDate()).getBytes(UTF_8);
		int transactionDateSize = transactionDateBytes.length;
		
		ByteBuffer buf = ByteBuffer.allocate(4 + customerIdSize + 4 + transactionDateSize);
		buf.putInt(customerIdSize);
		buf.put(customerIdBytes);
		buf.putInt(transactionDateSize);
		buf.put(transactionDateBytes);
		
		return buf.array();
	}
	
	@Override
	public void close() {
		log.info("留空, 不需要实现");
	}
}
