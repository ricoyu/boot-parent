package com.loserico.boot.kafka.zmart.model;

import java.util.Date;

/**
 * <p>
 * Copyright: (C), 2020-09-25 16:21
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public class Supplier {
	
	private int supplierId;
	
	private String supplierName;
	
	private Date supplierStartDate;
	
	public Supplier(int id, String name, Date dt) {
		this.supplierId = id;
		this.supplierName = name;
		this.supplierStartDate = dt;
	}
	
	public int getID() {
		return supplierId;
	}
	
	public String getName() {
		return supplierName;
	}
	
	public Date getStartDate() {
		return supplierStartDate;
	}
}
