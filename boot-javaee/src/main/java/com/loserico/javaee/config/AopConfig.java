package com.loserico.javaee.config;

import com.loserico.common.spring.aspect.SmartLoggerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Copyright: (C), 2020/4/11 17:54
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class AopConfig {
	
	@Bean
	public SmartLoggerAspect smartLoggerAspect() {
		return new SmartLoggerAspect();
	}
}
