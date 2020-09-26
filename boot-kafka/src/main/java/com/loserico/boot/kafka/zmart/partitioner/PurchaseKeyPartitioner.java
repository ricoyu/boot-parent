package com.loserico.boot.kafka.zmart.partitioner;

import com.loserico.boot.kafka.zmart.model.PurchaseKey;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

/**
 * <p>
 * Copyright: (C), 2020-09-25 15:40
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class PurchaseKeyPartitioner extends DefaultPartitioner {
	
	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		Object newKey = null;
		if (key != null) {
			PurchaseKey purchaseKey = (PurchaseKey) key;
			newKey = purchaseKey.getCustomerId();
			keyBytes = ((String) newKey).getBytes();
		}
		
		System.out.println(((String)newKey).hashCode() % 3);
		return super.partition(topic, newKey, keyBytes, value, valueBytes, cluster);
	}
}
