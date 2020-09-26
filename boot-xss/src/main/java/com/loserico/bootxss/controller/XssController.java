package com.loserico.bootxss.controller;

import com.loserico.bootxss.bean.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "")
public class XssController {
	
	@PostMapping(value = "/xss")
	public Object test(String name) {
		System.out.println(name);
		return name;
	}
	
	@PostMapping(value = "/json")
	public Object testJSON(@RequestBody Param param) {
		return param;
	}
	
	@GetMapping(value = "/query")
	public Object testQuery(String q) {
		return q;
	}
	
	@GetMapping(value = "/readFromDb")
	public Object readFromDb() {
		Param param = new Param();
		param.setName("<script>alert('hi')</script>");
		param.setAge(18);
		return param;
	}
	
	@GetMapping("/header")
	public Object getHeader(@RequestHeader("name") String value, HttpServletRequest request) {
		System.out.println(request.getHeader("name"));
		return value;
	}
	
	@GetMapping("/headers")
	public Object getHeaders(HttpServletRequest request) {
		Enumeration<String> values = request.getHeaders("name");
		List<String> results = new ArrayList<>();
		while (values.hasMoreElements()) {
			String value = values.nextElement();
			log.info(value);
			results.add(value);
		}
		
		return results;
	}
}