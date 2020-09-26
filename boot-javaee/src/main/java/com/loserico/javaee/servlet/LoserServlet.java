package com.loserico.javaee.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p>
 * Copyright: (C), 2020/4/10 19:52
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class LoserServlet implements Servlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("<<<<<< {} init", LoserServlet.class);
	}
	
	@Override
	public ServletConfig getServletConfig() {
		log.info("<<<<<< {} getServletConfig()", LoserServlet.class);
		return null;
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		log.info("<<<<<< request: {}, response: {}", request.getClass(), response.getClass());
	}
	
	@Override
	public String getServletInfo() {
		log.info("<<<<<< {} getServletInfo", LoserServlet.class);
		return null;
	}
	
	@Override
	public void destroy() {
		log.info("<<<<<< {} destroy", LoserServlet.class);
	}
}
