package com.loserico.boot.kafka.zmart.model;

import java.util.Date;
import java.util.Objects;

/**
 *  
 * <p>
 * Copyright: Copyright (c) 2020-09-25 15:39
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
public class PurchaseKey {
	
	private String customerId;
	private Date transactionDate;
	
	public PurchaseKey(String customerId, Date transactionDate) {
		this.customerId = customerId;
		this.transactionDate = transactionDate;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PurchaseKey)) {
			return false;
		}
		PurchaseKey that = (PurchaseKey) o;
		return Objects.equals(customerId, that.customerId) &&
				Objects.equals(transactionDate, that.transactionDate);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(customerId, transactionDate);
	}
}
