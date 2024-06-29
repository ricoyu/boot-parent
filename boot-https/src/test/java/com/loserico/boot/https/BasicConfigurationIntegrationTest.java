package com.loserico.boot.https;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * <p>
 * Copyright: (C), 2020-8-3 0003 10:33
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicConfigurationIntegrationTest {
	
	private static final String WELCOME_URL = "https://localhost:8443/welcome";
	
	private TestRestTemplate restTemplate;
	
	private URL base;
	
	@LocalServerPort
	private int port;
	
	@Value("${trust.store}")
	private Resource trustStore;
	
	@Value("${trust.store.password}")
	private String trustStorePassword;
	
	@Before
	public void setup() throws MalformedURLException {
		restTemplate = new TestRestTemplate("rico", "0987654321");
		base = new URL("http://localhost:" + port);
	}
	
	@Test
	public void whenLoggedUserRequestsHomePage_ThenSuccess() {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
		assertEquals(OK, response.getStatusCode());
		boolean contains = response.getBody().contains("Rico");
		assertTrue(contains);
	}
	
	@Test
	public void whenUserWithWrongCredentials_thenUnauthorizedPage() {
		restTemplate = new TestRestTemplate("rico", "123456");
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
		
		assertEquals(UNAUTHORIZED, response.getStatusCode());
		assertTrue(response.getBody() == null);
	}
	
	
	public RestTemplate restTemplate() throws Exception {
		/*SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
				.build();
		
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
		
		HttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(socketFactory)
				.build();
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(factory);*/
		return null;
	}
	
	@Test
	public void whenGETanHTTPSResource_thenCorrectResponse() throws Exception {
		ResponseEntity<String> response = restTemplate().getForEntity(WELCOME_URL, String.class, Collections.emptyMap());
		assertEquals("<h1>Welcome to Secured Site</h1>", response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
