package com.loserico.helloboot.service;

import com.loserico.common.spring.annotation.PostInitialize;
import com.loserico.helloboot.entity.TcpMatrix;
import com.loserico.helloboot.enums.Direction;
import com.loserico.helloboot.enums.EquipmentType;
import com.loserico.helloboot.enums.MessageType;
import com.loserico.orm.dao.EntityOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Copyright: (C), 2024-01-24 14:41
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
@Transactional
public class TcpMatrixService {
	
	@Autowired
	private EntityOperations entityOperations;
	
	@PostInitialize
	public void initTcpMetrix() {
		TcpMatrix tcpMatrix1 = new TcpMatrix();
		tcpMatrix1.setSeq(101);
		tcpMatrix1.setEquipmentType(EquipmentType.TAMR);
		tcpMatrix1.setEquipmentId("EQ-12345");
		tcpMatrix1.setMsgType(MessageType.MSG);
		tcpMatrix1.setDirection(Direction.IN);
		tcpMatrix1.setAck(true);
		tcpMatrix1.setReceived(false);
		tcpMatrix1.setMsg("Message Content 1");
		tcpMatrix1.setReplySeq(1001);
		tcpMatrix1.setCreator("User1");
		tcpMatrix1.setCreateTime(LocalDateTime.now());
		tcpMatrix1.setModifier("User2");
		tcpMatrix1.setModifyTime(LocalDateTime.now());

		TcpMatrix tcpMatrix2 = new TcpMatrix();
		tcpMatrix2.setSeq(102);
		tcpMatrix2.setEquipmentType(EquipmentType.LIFT);
		tcpMatrix2.setEquipmentId("EQ-67890");
		tcpMatrix2.setMsgType(MessageType.REP);
		tcpMatrix2.setDirection(Direction.OUT);
		tcpMatrix2.setAck(false);
		tcpMatrix2.setReceived(true);
		tcpMatrix2.setMsg("Message Content 2");
		tcpMatrix2.setReplySeq(1002);
		tcpMatrix2.setCreator("User3");
		tcpMatrix2.setCreateTime(LocalDateTime.now());
		tcpMatrix2.setModifier("User4");
		tcpMatrix2.setModifyTime(LocalDateTime.now());

		List<TcpMatrix> tcpMatrices = new ArrayList<>();
		tcpMatrices.add(tcpMatrix1);
		tcpMatrices.add(tcpMatrix2);
		entityOperations.save(tcpMatrices);
	}
}
