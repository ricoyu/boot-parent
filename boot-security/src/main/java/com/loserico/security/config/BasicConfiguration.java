package com.loserico.security.config;

import org.springframework.context.annotation.Configuration;

/**
 * Spring Security 精细化配置
 * <p>
 * Copyright: (C), 2021-02-23 11:08
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
//@EnableWebSecurity
public class BasicConfiguration {
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth.inMemoryAuthentication()
				.withUser("rico")
				.password(encoder.encode("654321"))
				.roles("USER")
				.and()
				.withUser("admin")
				.password(encoder.encode("123456"))
				.roles("USER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}*/
}
