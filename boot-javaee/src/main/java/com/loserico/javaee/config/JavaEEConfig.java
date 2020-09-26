package com.loserico.javaee.config;

import com.loserico.javaee.LoserFilter;
import com.loserico.javaee.servlet.LoserServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Copyright: (C), 2020/4/10 19:58
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class JavaEEConfig {
	
	@Bean
	public ServletRegistrationBean loserServlet() {
		ServletRegistrationBean<LoserServlet> servletRegistrationBean = new ServletRegistrationBean<>();
		servletRegistrationBean.setServlet(new LoserServlet());
		servletRegistrationBean.addUrlMappings("/loser/*");
		servletRegistrationBean.setLoadOnStartup(0);
		return servletRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<LoserFilter> loserFilter() {
		FilterRegistrationBean<LoserFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LoserFilter());
		filterRegistrationBean.addUrlPatterns("/users/*", "/loser/*", "/service/*");
		filterRegistrationBean.addInitParameter("birthday", "1982-11-09");
		return filterRegistrationBean;
	}
}
