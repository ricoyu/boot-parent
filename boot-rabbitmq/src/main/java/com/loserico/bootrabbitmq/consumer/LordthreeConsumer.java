package com.loserico.bootrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class LordthreeConsumer {
	
	@RabbitListener(queues = "lordthreeDirectQueue")
	public void consumerMsg(Message message) {
		
		System.out.println("消费消息:" + message.getPayload().toString());
	}
	
	@RabbitListener(queues = "lordthreeFanoutQueue1")
	public void consumerFanoutMsg(Message message) {
		System.out.println("消费消息:" + message.getPayload().toString());
	}
	
	@RabbitListener(queues = "lordthreeFanoutQueue2")
	public void consumerFanoutMsg2(Message message) {
		System.out.println("消费消息:" + message.getPayload());
	}
	
	@RabbitListener(queues = "lordthreeTopicQueue")
	public void consumerTopicMsg(Message message) {
		System.out.println("消费消息:" + message.getPayload());
	}
	
	@RabbitListener(queues = "lordthreeTopicQueue2")
	public void consumerTopicMsg2(Message message) {
		System.out.println("消费消息:" + message.getPayload());
	}
	
}