package com.loserico.bootrabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LordthreeProductor {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMsgDirect(String msg) {
		Map<String, Object> sendContext = new HashMap<>();
		sendContext.put("name", "张三");
		sendContext.put("sex", "男");
		sendContext.put("msg", msg);
		rabbitTemplate.convertAndSend("lordthreeDirectExchange", "lordthree.directkey", sendContext);
	}
	
	/**
	 * 发送到扇形交换机上
	 *
	 * @param msg
	 */
	public void sendMsgFanout(String msg) {
		rabbitTemplate.convertAndSend("lordthreeFanoutExchange", "aaaabbdd", msg);
	}
	
	/**
	 * 发送到Topic交换机上
	 *
	 * @param msg
	 */
	public void sendMsgTopic(String msg) {
		rabbitTemplate.convertAndSend("lordthreeTopicExchange", "topic.key.aaa", msg);
		rabbitTemplate.convertAndSend("lordthreeTopicExchange", "aa.key", msg);
	}
	
}