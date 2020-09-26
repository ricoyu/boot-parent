package com.loserico.bootrabbitmq.config;

import com.loserico.bootrabbitmq.aspect.RabbitListenerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * Copyright: (C), 2020/4/13 10:57
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@EnableAspectJAutoProxy
@Configuration
public class AspectConfig {
	
	@Bean
	public RabbitListenerAspect rabbitListenerAspect() {
		return new RabbitListenerAspect();
	}
}
