package com.loserico.bootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Copyright: (C), 2020/4/12 21:45
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class RabbitMQConfig {
	
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	//----------------------------------------------------------------
	
	@Bean
	public DirectExchange directExchange() {
		/*
		 * durable:表示消息是否可持久化
		 * autoDelete:表示若没有队列和此交换机绑定 就直接删除该交换机
		 */
		return new DirectExchange("lordthreeDirectExchange", true, false);
	}
	
	@Bean
	public Queue directQueue() {
		return new Queue("lordthreeDirectQueue", true, false, false);
	}
	
	@Bean
	public Binding directBinding() {
		Binding binding = BindingBuilder.bind(directQueue()).to(directExchange()).with("lordthree.directkey");
		return binding;
	}
	
	//----------------------------------------------------------------
	
	/**
	 * 扇形交换机
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("lordthreeFanoutExchange",true,false);
	}
	
	@Bean
	public Queue fanoutQueue1() {
		return new Queue("lordthreeFanoutQueue1",true,false,false);
	}
	
	@Bean
	public Queue fanoutQueue2() {
		return new Queue("lordthreeFanoutQueue2",true,false,false);
	}
	
	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
		
	}
	
	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
	}
	
	//----------------------------------------------------------------
	
	/**
	 * 主题交换机
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange("lordthreeTopicExchange",true,false);
	}
	
	@Bean
	public Queue topicQueue() {
		return new Queue("lordthreeTopicQueue",true,false,false);
	}
	
	@Bean
	public Queue topicQueue2() {
		return new Queue("lordthreeTopicQueue2",true,false,false);
	}
	
	@Bean
	public Binding topicBinding1(){
		return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.key.#");
		
	}
	
	@Bean
	public Binding topicBinding2(){
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("#.key");
		
	}
}
