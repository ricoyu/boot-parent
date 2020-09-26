package com.loserico.javaee;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p>
 * Copyright: (C), 2020/4/10 20:09
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class LoserFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info(">>>>>> filter name: {}, filter class: {}, init param birthday: {}", filterConfig.getFilterName(), LoserFilter.class, filterConfig.getInitParameter("birthday"));
	}
	
	@Override
	public void destroy() {
		log.info(">>>>>> {} destory", LoserFilter.class);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info(">>>>>> request: {}, response: {}", request.getClass(), response.getClass());
		chain.doFilter(request, response);
	}
}
