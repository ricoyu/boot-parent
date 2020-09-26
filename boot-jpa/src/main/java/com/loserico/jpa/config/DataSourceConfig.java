package com.loserico.jpa.config;

import com.loserico.orm.dao.JpaDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Copyright: (C), 2020/4/14 18:00
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {
	
	@Bean
	public JpaDao jpaDao() {
		return new JpaDao();
	}
}
