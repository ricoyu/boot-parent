package com.loserico.bootjdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Copyright: (C), 2020/4/12 11:23
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(DruidDataSourceProperties.class)
public class DruidDataSourceConfig {
	
	@Autowired
	private DruidDataSourceProperties druidDataSourceProperties;
	
	@Bean
	public DataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(druidDataSourceProperties.getUsername());
		dataSource.setPassword(druidDataSourceProperties.getPassword());
		dataSource.setUrl(druidDataSourceProperties.getUrl());
		dataSource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
		dataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
		dataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
		dataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
		dataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
		dataSource.setFilters(druidDataSourceProperties.getFilters());
		dataSource.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
		return dataSource;
	}
	
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
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
		
		Map<String, Object> initParams = new HashMap<>();
		initParams.put("exclusions", "*.js,*.css, /druid/*");
		filterRegistrationBean.setInitParameters(initParams);
		return filterRegistrationBean;
	}
}
