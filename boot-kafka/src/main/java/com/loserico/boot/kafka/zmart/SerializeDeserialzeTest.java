package com.loserico.boot.kafka.zmart;

import com.loserico.boot.kafka.zmart.deserializer.SupplierDeserializer;
import com.loserico.boot.kafka.zmart.model.Supplier;
import com.loserico.boot.kafka.zmart.serializer.SupplierSerializer;

import java.util.Date;

/**
 * <p>
 * Copyright: (C), 2020-09-25 16:52
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class SerializeDeserialzeTest {
	
	public static void main(String[] args) {
		Supplier supplier = new Supplier(1, "三少爷", new Date());
		SupplierSerializer supplierSerializer = new SupplierSerializer();
		byte[] bytes = supplierSerializer.serialize("", supplier);
		SupplierDeserializer deserializer = new SupplierDeserializer();
		Supplier deserialize = deserializer.deserialize("", bytes);
		System.out.println(deserialize.getID());
		System.out.println(deserialize.getName());
		System.out.println(deserialize.getStartDate());
	}
}
