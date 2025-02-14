package com.loserico.jpa.service;

import com.github.javafaker.Faker;
import com.loserico.jpa.annotation.RedisListener;
import com.loserico.jpa.entity.Outbound;
import com.loserico.orm.dao.EntityOperations;
import com.loserico.orm.dao.SQLOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Copyright: (C), 2020/4/14 17:03
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Service
@Transactional
public class MockService {
	
	@Autowired
	private EntityOperations entityOperations;

	@Autowired
	private SQLOperations sqlOperations;
	
	private AtomicInteger counter = new AtomicInteger(0);
	
	private Faker faker = new Faker();

	public static enum ExportType {
		/** 预览 */
		PREVIEW,
		/** 报告 */
		REPORT
		;
	}

	@RedisListener
	public void mockOutbound(int count) {
		if (count > 10000) {
			for (int i = 0; i < 10; i++) {
				mockOutbound(count / 10);
			}
		}
		int total = 0;
		List<Outbound> outbounds = new ArrayList<>();
		Outbound outbound = new Outbound();
		
		outbound.setOrderId("ORD" + faker.number().digits(5));
		outbound.setDdTenantId("00000");
		outbound.setSourceOrderId("SRC" + faker.number().digits(5));
		outbound.setQty(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
		outbound.setItemQty(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
		outbound.setNoFinishedQty(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
		outbound.setNoFinishedItem(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
		outbound.setMemo(faker.lorem().sentence());
		outbound.setAddress(faker.address().fullAddress());
		outbound.setLinkMan(faker.name().fullName());
		String phone = faker.phoneNumber().phoneNumber();
		if (phone.length() > 20) {
			phone = phone.substring(0, 20);
		}
		outbound.setPhone(phone);
		outbound.setStorageId("STO" + faker.number().numberBetween(1000, 9999));
		outbound.setStorageName("Storage " + faker.number().numberBetween(1, 100));
		outbound.setPrintCount(BigDecimal.valueOf(faker.number().numberBetween(0, 10)));
		outbound.setSourceFlg("FLG" + faker.number().numberBetween(1, 10));
		outbound.setSkuName("SKU " + faker.number().numberBetween(1, 1000));
		outbound.setSkuId("SKUID" + faker.number().numberBetween(1000, 9999));
		outbound.setSourceOrderMode("MODE" + faker.number().numberBetween(1, 5));
		outbound.setCustomerOrderId("CUSTORD" + faker.number().digits(5));
		outbound.setUuid(faker.internet().uuid());
		outbound.setProcessQty(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
		outbound.setBusinessSystemId("BSID" + faker.number().numberBetween(1, 100));
		outbound.setOutboundTypeId("OTID" + faker.number().numberBetween(1, 100));
		outbound.setOutboundTypeName("Outbound Type " + faker.number().numberBetween(1, 100));
		outbounds.add(outbound);
		
		entityOperations.save(outbounds);
		log.info("累积插入{}条outbound数据", counter.addAndGet(outbounds.size()));
	}
}
