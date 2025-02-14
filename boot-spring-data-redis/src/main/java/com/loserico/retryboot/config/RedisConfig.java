package com.loserico.retryboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <p>
 * Copyright: (C), 2024-01-15 17:56
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
//@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String host1;
	
	@Value("${spring.redis.password}")
	private String password1;
	
	@Value("${spring.redis2.host}")
	private String host2;
	
	@Value("${spring.redis2.password}")
	private String password2;
	
	@Primary
	@Bean
	public RedisConnectionFactory redisConnectionFactory1() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host1, 6379);
		config.setPassword(password1);
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
		return factory;
	}
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory2() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host2, 6379);
		config.setPassword(password2);
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
		return factory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate1() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory1());
		//序列化和反序列化redis的value值
		template.setKeySerializer(RedisSerializer.string());
		template.setHashKeySerializer(RedisSerializer.string());
		
		template.setHashValueSerializer(RedisSerializer.string());
		template.setValueSerializer(RedisSerializer.string());
		return template;
	}
	
	@Bean
	public RedisTemplate<Object, Object> redisTemplate2() {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory2());
		//序列化和反序列化redis的value值
		template.setKeySerializer(RedisSerializer.string());
		template.setHashKeySerializer(RedisSerializer.string());
		
		template.setHashValueSerializer(RedisSerializer.string());
		template.setValueSerializer(RedisSerializer.string());
		return template;
	}
}
