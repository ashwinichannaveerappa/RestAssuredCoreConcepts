package com.rest.api.get;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {

	//common fotr all the tes tcases t1,t2,t3 --validation
	//status code
	//content type
	//header
	
		ResponseSpecBuilder res=new ResponseSpecBuilder();
		ResponseSpecification respspe200c= res
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectHeader("Server","nginx")
				.build();
	
		ResponseSpecification respec400_BAD_REQUEST= res
				.expectStatusCode(400)
				.expectHeader("Server","nginx")
				.build();
	
		ResponseSpecification respec401_AUTH_FAIL= res
				.expectStatusCode(401)
				.expectHeader("Server","nginx")
				.build();
	
	@Test
	public void ResponseSpecTest() {
		RestAssured.baseURI="https://gorest.co.in";
		given()
			.header("Authorization","Bearer 45db930d2442f97e86c68ecc3f222ee788cbd7c2b685aaa0348c59bc69cc9f85")
			.when()
				.get("/public-api/users")
			.then()
				.assertThat()
					.spec(respspe200c);
		
	}
	
	@Test(testName = "negative test")
	public void ResponseSpec_Auth_Fail_Test() {
		RestAssured.baseURI="https://gorest.co.in";
		given()
			.header("Authorization","Bearer 45dbssd2442f97e86c68ecc3f222ee788cbd7c2b685aaa0348c59bc69cc9f85")
			.when()
				.get("/public-api/users")
			.then()
				.assertThat()
					.spec(respec401_AUTH_FAIL);
	}
	
	
}
