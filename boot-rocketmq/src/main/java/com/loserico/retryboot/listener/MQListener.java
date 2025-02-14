package com.loserico.retryboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>
 * Copyright: (C), 2023-10-13 17:21
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "canal_equipment_topic", consumerGroup = "equipment_group")
public class MQListener implements RocketMQListener<MessageExt> {
	
	private AtomicInteger counter = new AtomicInteger(0);
	
	@Override
	public void onMessage(MessageExt message) {
		log.info("线程["+Thread.currentThread().getId()+"], 队列:["+message.getQueueId()+"], 收到消息:[{}]", new String(message.getBody(), UTF_8));
		//throw new RuntimeException("消费失败" + counter.getAndIncrement()+"次");
	}
}
