package com.rest.api.authentications;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;

public class AuthApis {

	//basic auth:
	//username/password
	
	@Test
	public void basic_auth_Preemptive_API_Test() {
		given().log().all()
		.auth()
		.preemptive()
			.basic("admin", "admin")
		.when().log().all()
			.get("http://the-internet.herokuapp.com/basic_auth")
		.then()
			.assertThat()
				.statusCode(200);
	}
	
	@Test
	public void basic_auth_Without_Preemptive_API_Test() {
		given().log().all()
		.auth()
			.basic("admin", "admin")
		.when().log().all()
			.get("http://the-internet.herokuapp.com/basic_auth")
		.then()
			.assertThat()
				.statusCode(200);
	}
	
	//digest -Un and PWD - its type of http authentication
	
	@Test
	public void basic_auth_digest_API_Test() {
		given().log().all()
		.auth()
			.digest("admin", "admin")
		.when().log().all()
			.get("http://the-internet.herokuapp.com/basic_auth")
		.then()
			.assertThat()
				.statusCode(200);
	}
	
	
	//form based
	@Test
	public void basic_auth_form_API_Test() {
		given().log().all()
		.auth()
			.form("admin", "admin", new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm","username","password"))
		.when().log().all()
			.get("https://classic.freecrm.com/system/authenticate.cfm")
		.then()
			.assertThat()
				.statusCode(200);
	}
	
	
	//Oauth 2.0
	//accesstoken
	
	@Test
	public void OAuth2_API_test(){
		given()
			.auth()
				.oauth2("Bearer 45db930d2442f97e86c68ecc3f222ee788cbd7c2b685aaa0348c59bc69cc9f85")
		.when()
			.get("https://gorest.co.in/public-api/users?first_name=Elva")
		.then()
			.assertThat()
				.statusCode(200);
	}
	
	//header format -- Oauth2.0
	
	@Test
	public void OAuth2_API_Test_With_AuthHeader() {
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
			.contentType("application/json")
			.header("Authorization","Bearer 45db930d2442f97e86c68ecc3f222ee788cbd7c2b685aaa0348c59bc69cc9f85")
		.when().log().all()
			.get("/public-api/users?first_name=Elva")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
			.header("Server", "nginx");
	}
	
	
	//Oauth2 with Two Query parameters
	@Test
	public void OAuth_API_WithTwoParams_API_Test() {
		RestAssured.baseURI="https://gorest.co.in";

		given()
			.contentType("application/json")
			.header("Authorization","Bearer 45db930d2442f97e86c68ecc3f222ee788cbd7c2b685aaa0348c59bc69cc9f85")
			.queryParam("first_name", "John")
			.queryParam("gender", "female")
		.when().log().all()
			.get("/public-api/users")
		.then().log().all()
			.statusCode(200)
			.and()
			.header("Server", "nginx");	}
	
}
