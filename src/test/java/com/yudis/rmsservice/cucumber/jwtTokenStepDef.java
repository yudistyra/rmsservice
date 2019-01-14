package com.yudis.rmsservice.cucumber;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import com.yudis.rmsservice.payloads.LoginRequest;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class jwtTokenStepDef extends SpringIntegrationTest {
	private LoginRequest loginParam;
	
	@Before
	public void setUp() {
		loginParam = new LoginRequest();
		loginParam.setUsername("admin");
		loginParam.setPassword("123456");
	}
	
	@When("^client calls /generate-token$")
	public void client_calls_generate_token() throws Throwable {
	    apiLogin("/api/auth/signin", loginParam);
	}

	@Then("^the client get response code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {
	    assertThat("status code is incorrect : " + response.getBody(), response.getStatusCodeValue(), is(statusCode));
	}
	
	@And("^the client receives generated token$")
	public void the_client_receives_generated_token() throws Throwable {
		JSONAssert.assertEquals("{status:200, message:success, jwtResponse:{accessToken:x, tokenType:Bearer}}", response.getBody(),  
				  new CustomComparator(
				  JSONCompareMode.STRICT, 
				  new Customization("jwtResponse.accessToken", 
				  new RegularExpressionValueMatcher<Object>("(.+)"))));
	}
}
