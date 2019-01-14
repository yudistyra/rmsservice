package com.yudis.rmsservice.cucumber;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.yudis.rmsservice.RmsserviceApplication;
import com.yudis.rmsservice.payloads.LoginRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RmsserviceApplication.class, webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	ResponseEntity<String> response = null;
	
	public void apiGet(String url) {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		response = restTemplate.exchange(
				createURLWithPort(url),
				HttpMethod.GET, entity, String.class);		
	}
	
	public void apiLogin(String url, LoginRequest loginRequest) {
		HttpEntity<LoginRequest> entity = new HttpEntity<LoginRequest>(loginRequest, headers);

		response = restTemplate.exchange(
				createURLWithPort(url),
				HttpMethod.POST, entity, String.class);		
	}
	
	public void apiPost(String url, String params) {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		response = restTemplate.exchange(
				createURLWithPort(url),
				HttpMethod.POST, entity, String.class);		
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:8080" + uri;
	}
}
