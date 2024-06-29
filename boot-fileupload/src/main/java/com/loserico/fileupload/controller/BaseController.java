package com.loserico.fileupload.controller;

import com.loserico.common.lang.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>
 * Copyright: (C), 2021-03-24 11:07
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
public class BaseController {
	
	protected void printHeaders(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String header = headerNames.nextElement();
			log.info("Header {}:{}", header, request.getHeader(header));
		}
	}
	
	protected void printParameters(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			log.info("param: {}={}", entry.getKey(), entry.getValue() == null ? null : StringUtils.join(entry.getValue()));
		}
	}
}
