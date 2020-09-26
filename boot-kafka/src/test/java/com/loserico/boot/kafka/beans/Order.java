package com.loserico.boot.kafka.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Copyright: (C), 2020-7-26 0026 9:05
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	private Integer orderId;
	private Integer productId;
	private Integer productNum;
	private Double orderAmount;
}
