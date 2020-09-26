package com.loserico.bootrabbitmq.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <p>
 * Copyright: (C), 2020/4/13 10:36
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Aspect
@Slf4j
public class RabbitListenerAspect {
	
	@Pointcut("@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
	public void pointcut() {
	}
	
	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {
		log.info("方法名: {}", joinPoint.getSignature().getName());
		log.info("参数: {}", joinPoint.getArgs());
	}
}
