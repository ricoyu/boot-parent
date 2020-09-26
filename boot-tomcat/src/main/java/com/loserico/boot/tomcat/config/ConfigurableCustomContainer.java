package com.loserico.boot.tomcat.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * <p>
 * Copyright: (C), 2020-08-04 11:51
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
//@Configuration
public class ConfigurableCustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	
	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		//factory.setPort(8081);
	}
}
