package com.loserico.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * <p>
 * Copyright: (C), 2021-02-23 11:22
 * <p>
 * <p>
 * Company: Information & Data Security Solutions Co., Ltd.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicConfigurationIntegrationTest {
	
	private TestRestTemplate restTemplate;
	
	private URL base;
	
	@LocalServerPort
	private int port;
	
	@Before
	public void setup() throws MalformedURLException {
		restTemplate = new TestRestTemplate("rico", "654321");
		base = new URL("http://localhost:" + port + "/security");
	}
	
	@Test
	public void testWhenLoggedUserRequestsHomePage_ThenSuccess() {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("Rico"));
	}
	
	@Test
	public void testWhenUserWithWrongCredentials_thenUnauthorizedPage() {
		restTemplate = new TestRestTemplate("rico", "wrongpassword");
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
}
