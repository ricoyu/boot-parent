package com.loserico.boot.tomcat.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Copyright: (C), 2020-08-04 12:04
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class HttpConnectorRedirectConfiguration {
	
	@Value("${http.port}")
	private int httpPort;
	
	@Value("${server.port}")
	private int httpsPort;
	
	@Value("${server.tomcat.max-connections}")
	private int maxConnections;
	
	@Value("${server.tomcat.max-threads}")
	private int maxThreads;
	
	@Value("${server.tomcat.accept-count}")
	private int acceptCount;
	
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			/**
			 * HTTP重定向到HTTPS需要Override这个方法, 不知道为什么?
			 * @param context
			 */
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
				/*
				 * 在访问相关资源时使用任何传输层保护
				 * NONE：对所用的通讯协议不加限制
				 * CONFIDENTIAL：他们都只是简单地要求用SSL，一般是自动采用30x跳转
				 * INTEGRAL：类似CONFIDENTIAL
				 */
				constraint.setUserConstraint("CONFIDENTIAL");
				//constraint.setAuthConstraint(true);
				
				SecurityCollection collection = new SecurityCollection();
				collection.addOmittedMethod("PUT");
				collection.addOmittedMethod("DELETE");
				collection.addOmittedMethod("HEAD");
				collection.addOmittedMethod("OPTIONS");
				collection.addOmittedMethod("TRACE");
				collection.addPattern("/*");
				
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(createHttpConnector());
		return tomcat;
	}
	
	private Connector createHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(httpPort);
		connector.setSecure(false);
		connector.setRedirectPort(httpsPort);
		
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		//设置最大连接数
		protocol.setMaxConnections(maxConnections);
		//设置最大线程数
		protocol.setMaxThreads(maxThreads);
		//当tomcat起动的线程数达到最大时, 接受排队的请求个数, 默认值为100
		protocol.setAcceptCount(acceptCount);
		return connector;
	}
}
