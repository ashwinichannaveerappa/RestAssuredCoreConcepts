package com.rest.api.authentications;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth2APITests {

	
	@Test
	public void checkOAuth2_APITest() {
		RequestSpecification request =
			RestAssured.
			given()
				.auth()
					.oauth2("4bb96fcf1c71d80ab6aaa853162f8c3365589898");
		
	Response response = request.post("http://coop.apps.symfonycasts.com/api/1368/chickens-feed");
	System.out.println(response.getStatusCode());
	System.out.println(response.prettyPrint());
	}
	
	//1.Generate a token at the run time by using token api
	//2. String tokenID from the response
	//3. Hit the next api with this tokenID
	//4.with Oauth2.0 - with oauth2(): No need to add Bearer keyword,just pass the token value
	
	@Test
	public void getAuth2TokenAPITest() {
		RequestSpecification request = RestAssured.given()
		.formParam("client_id", "testtest")
		.formParam("client_secret","cfaafc1ba1a11a9a5baa2b56d8836212")
		.formParam("grant_type","client_credentials")
		.formParam("response_type", "code");
		
		Response response = request.post("http://coop.apps.symfonycasts.com/token");
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		//to get TokenId
		String tokenID=response.jsonPath().getString("access_token");
		System.out.println("API token id is: "+tokenID);
		
		//feed chicken api by using token id
		RequestSpecification request1 =
				RestAssured.
				given()
					.auth()
						.oauth2(tokenID);
			
		Response response1 = request1.post("http://coop.apps.symfonycasts.com/api/1368/chickens-feed");
		System.out.println(response1.getStatusCode());
		System.out.println(response1.prettyPrint());
		
	}
	
}
