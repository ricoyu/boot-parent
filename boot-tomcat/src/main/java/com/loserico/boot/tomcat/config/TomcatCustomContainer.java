package com.loserico.boot.tomcat.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

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
public class TomcatCustomContainer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
	
	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		//factory.setContextPath("/customize");
		//factory.setPort(8082);
	}
}
