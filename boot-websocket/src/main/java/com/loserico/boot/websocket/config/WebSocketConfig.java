package com.loserico.boot.websocket.config;

import com.loserico.boot.websocket.filter.WebSocketAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 这个配置类检测带注解@ServerEndpoint的bean并注册它们
 * 
 * <p>
 * Copyright: (C), 2020-7-23 0023 16:37
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Configuration
public class WebSocketConfig {
	
	/**
	 * 给spring容器注入这个ServerEndpointExporter对象, 它会检测所有带有@serverEndpoint注解的bean并注册他们。
	 * @return
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
	
	@Bean
	public WebSocketAuthFilter webSocketAuthFilter() {
		return new WebSocketAuthFilter();
	}
}
