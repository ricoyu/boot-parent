package com.loserico.bootrabbitmq.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020/4/12 18:24
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class DruidConfig {
	
	/**
	 * http://localhost:8080/druid
	 * @return
	 */
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("loginUsername", "admin");
		initParameters.put("loginPassword", "123456");
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}
	
}
